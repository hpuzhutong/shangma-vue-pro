package com.zhu.sm.controller;

import com.github.pagehelper.PageHelper;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.http.AxiosStatus;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.common.util.UploadImgService;
import com.zhu.sm.common.util.UploadUtils;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.controller.base.BaseController;
import com.zhu.sm.dto.AdminDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.query.AdminQuery;
import com.zhu.sm.service.AdminService;
import com.zhu.sm.transfer.AdminTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.function.IntFunction;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:18
 * @className: BrandController
 * @description:
 */
@RestController
@RequestMapping("admin")
public class AdminController extends BaseController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("searchPage")
    public AxiosResult<PageBean<AdminDTO>> searchPage(AdminQuery adminQuery) {
        PageHelper.startPage(adminQuery.getCurrentPage(), adminQuery.getPageSize());
        PageBean<AdminDTO> pageBean = adminService.searchPage(adminQuery);
        return AxiosResult.success(pageBean);
    }

    //TODO
    @GetMapping
    public AxiosResult<List<Admin>> findAll() {
        return AxiosResult.success(adminService.findAll());
    }

    @GetMapping("{id}")
    public AxiosResult<AdminDTO> findById(@PathVariable long id) {
        AdminDTO adminDTO = adminService.getAdminAndRoleByAdminId(id);
        return AxiosResult.success(adminDTO);
    }

    /**
     * MD5加密
     *
     * @param admin
     * @return
     */
    @PostMapping
    public AxiosResult addAdmin(@Validated(AddGroup.class) @RequestBody Admin admin) {
        //判断用户名 邮箱  手机号 身份证号时候存在    及用户是否存在
        boolean row = adminService.hasAccountEmailPhone(admin);
        if (row){
            return AxiosResult.error(AxiosStatus.USED_HAS_EXIST);
        }else{
            if (admin.getIsActive() == null) {
                admin.setIsActive(false);
            }
            if (admin.getIsAdmin() == null) {
                admin.setIsAdmin(false);
            }
            String encode = bCryptPasswordEncoder.encode("123");
            admin.setAdminPassword(encode);

//            return toAxios(adminService.add(admin));

            return toAxios(adminService.addAdminAndAdminRole(admin));
        }

//        int row = adminService.findByAccount(admin.getAdminAccount());
//        if (row == 1) {
//            return AxiosResult.error(AxiosStatus.ACCOUNT_USED_ERROR);
//        } else {
//            if (admin.getIsActive() == null) {
//                admin.setIsActive(false);
//            }
//            if (admin.getIsAdmin() == null) {
//                admin.setIsAdmin(false);
//            }
//            String encode = bCryptPasswordEncoder.encode("123");
//            admin.setAdminPassword(encode);
//            return toAxios(adminService.add(admin));
//        }
    }

    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable long id) {

//        return toAxios(adminService.deleteById(id));

        return toAxios(adminService.deleteAdminAndRoleByAdminId(id));

    }

    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteById(@PathVariable List<Long> ids) {
//        return toAxios(adminService.batchDelByIds(ids));

        return toAxios(adminService.batchDeleteAdminAndRolesByAdminIds(ids));
    }

    @PutMapping
    public AxiosResult<Void> updateById(@Validated(UpdateGroup.class) @RequestBody Admin admin) {
//        return toAxios(adminService.update(admin));

        return toAxios(adminService.updateAdminAndRole(admin));
    }

    /**
     * 上传图片
     *
     * @param avatar
     * @return
     * @throws IOException
     */
    @PostMapping("upload")
    public AxiosResult<String> upload(@RequestPart Part avatar) throws IOException {
        //方式一
        //  判断上传的是否是个真正的图片
        BufferedImage read = ImageIO.read(avatar.getInputStream());
        if (read == null) {
            return AxiosResult.error(AxiosStatus.NOT_IMG);
        }else{
            //重命名
            String filename = System.nanoTime() + "." + StringUtils.getFilenameExtension(avatar.getSubmittedFileName());
            return AxiosResult.success(UploadUtils.uploadImg(filename, avatar.getInputStream()));
        }

        //方式二   异常抛出  拿到的是null
//        return AxiosResult.success(uploadImgService.uploadImg(avatar));
    }
}
