package com.zhu.sm.controller;

import com.wf.captcha.ArithmeticCaptcha;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.http.AxiosStatus;
import com.zhu.sm.common.util.TokenService;
import com.zhu.sm.common.util.TreeUtil;
import com.zhu.sm.dto.MenuDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.entity.Menu;
import com.zhu.sm.service.AdminService;
import com.zhu.sm.service.MenuService;
import com.zhu.sm.service.RoleService;
import com.zhu.sm.transfer.MenuTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/3 21:11
 * @className: LoginController
 * @description:
 */

@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuTransfer menuTransfer;

    @Autowired
    private TokenService tokenService;


    private static final String CODE_KEY = "code_key:";  //redis数据存起来的时候是按 文件夹存储分类的,文件夹名字是code_key:


    @GetMapping("captcha/{uuid}")
    public AxiosResult<String> getCode(@PathVariable String uuid) {
        //算术类型验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(130, 48);
        //获取运算的结果  存入redis   保存时间1分钟
        String text = captcha.text();
        stringRedisTemplate.opsForValue().set(CODE_KEY + uuid, text, 180, TimeUnit.SECONDS);
        //使用base64格式输出
        String str = captcha.toBase64();
        return AxiosResult.success(str);
    }

    @PostMapping("")
    public AxiosResult<String> doLogin(@RequestBody Map<String, String> map) {

        String uuid = map.get("uuid");
        String code = map.get("code");
        String username = map.get("username");
        String password = map.get("password");

        //比对验证码是否正确
        String value = stringRedisTemplate.opsForValue().get(CODE_KEY + uuid);
        if (!StringUtils.isEmpty(value) && value.equals(code)) {
            //查询是否有此人
            Admin admin = adminService.getAdminByAccount(username);
            if (admin != null) {
                boolean matches = bCryptPasswordEncoder.matches(password, admin.getAdminPassword());
                Boolean isActive = admin.getIsActive();
                if (matches && isActive) {
                    //登录成功清除缓存数据
                    stringRedisTemplate.delete(CODE_KEY + uuid);
                    String token = tokenService.createToken(admin.getId());
                    return AxiosResult.success(token);
                }
                return AxiosResult.error(AxiosStatus.LOGIN_ERROR);
            }
            return AxiosResult.error(AxiosStatus.LOGIN_ERROR);
        }
        return AxiosResult.error(AxiosStatus.LOGIN_ERROR);

    }

    /**
     * 获取用户的菜单列表权限
     */
    @GetMapping("getMenuList")
    public AxiosResult<Map<String, Object>> getMenuList(HttpServletRequest httpServletRequest) {
        String authentication = httpServletRequest.getHeader("Authentication");
        //split空格的原因是在前端的baseaxios中加入了请求头Bearer+" " 以用来分割
        String s = authentication.split(" ")[1];
        Long adminId = tokenService.getAdminId(s);
        Admin admin = adminService.findById(adminId);
        Map<String, Object> map = new HashMap<>();
        map.put("admin",admin); //用户信息也返回前端
        //返回对应的权限菜单
        if (admin.getIsAdmin()) {
            //超级管理员
            List<Menu> all = menuService.findAll();
            List<MenuDTO> menuDTOS = menuTransfer.toDTO(all);
            List<MenuDTO> collect = menuDTOS.stream().filter(menuDTO -> !menuDTO.getMenuType().equals(3)).collect(Collectors.toList());
            List<MenuDTO> root = collect.stream().filter(menuDTO -> menuDTO.getParentId().equals(0L)).collect(Collectors.toList());
            TreeUtil.buildTreeData(root, collect);
            map.put("menuList",root);
            return AxiosResult.success(map);
        } else {
            //用户-》角色
            List<Long> roleIds = adminService.getRolesByAdminId(admin.getId());
            //角色-》权限
            List<MenuDTO> menuDTOS = roleService.getMenusByRoleIds(roleIds);
            //过滤掉按钮
            List<MenuDTO> collect = menuDTOS.stream().filter(menuDTO -> !menuDTO.getMenuType().equals(3)).collect(Collectors.toList());
            //封装到tree使之有层级关系
            List<MenuDTO> root = collect.stream().filter(menuDTO -> menuDTO.getParentId().equals(0L)).collect(Collectors.toList());
            TreeUtil.buildTreeData(root, collect);
            map.put("menuList",root);
            return AxiosResult.success(map);
        }
    }

}
