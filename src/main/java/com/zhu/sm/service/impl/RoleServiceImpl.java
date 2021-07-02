package com.zhu.sm.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.RoleDTO;
import com.zhu.sm.entity.*;
import com.zhu.sm.mapper.*;
import com.zhu.sm.query.RoleQuery;
import com.zhu.sm.service.RoleService;
import com.zhu.sm.service.base.impl.BaseServiceImpl;
import com.zhu.sm.transfer.RoleTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:11
 * @className: BrandServiceImpl
 * @description:
 */

/*
    两个实体类的替换
        b
 */

@Service
@Transactional
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Autowired
    private RoleTransfer roleTransfer;

    @Override
    public PageBean<RoleDTO> searchPage(RoleQuery roleQuery) {
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(roleQuery.getRoleName())) {
            lqw.like(Role::getRoleName, roleQuery.getRoleName());
        }
        if (Objects.nonNull(roleQuery.getStartTime()) && Objects.nonNull(roleQuery.getEndTime())) {
            //品牌的入库时间要在表单传来的时间之内
            lqw.between(Role::getCreateTime, roleQuery.getStartTime(), roleQuery.getEndTime());
        }

        List<Role> roles = roleMapper.selectList(lqw);
        PageInfo<Role> pageInfo = new PageInfo<>(roles);
        //把几何中的brands转成brandDTO
        List<RoleDTO> roleDTOS = roleTransfer.toDTO(roles);
        return PageBean.iniData(pageInfo.getTotal(), roleDTOS);
    }

    /**
     * 给角色设置权限
     */
    @Override
    public int setRoleMenu(Long roleId, List<Long> menuIds) {
        //删除已有的权限
        roleMenuMapper.delete(new LambdaUpdateWrapper<RoleMenu>().eq(RoleMenu::getMenuId, roleId));
        //添加权限
        menuIds.forEach(menuId -> roleMenuMapper.insert(new RoleMenu(roleId, menuId)));
        return 1;
    }

    /**
     * 获取角色的权限
     */
    @Override
    public List<Long> getMenusByRoleId(Long roleId) {
        List<Long> collect = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getRoleId, roleId))
                .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());

        if (!CollectionUtils.isEmpty(collect)) {
            //返回在权限列表中没有孩子的权限
            List<Menu> menus = menuMapper.selectBatchIds(collect);
            collect.clear();
            // 遍历menus  找出没有孩子的
            menus.forEach(menu -> {
                if (!menus.stream().anyMatch(item -> item.getParentId().equals(menu.getId()))) {
                    collect.add(menu.getId());
                }
            });
        }
        return collect;
    }


    @Override
    public int deleteCascade(long id) {
        //删除员工和角色的中间表 和本角色相关的数据
        adminRoleMapper.delete(new LambdaUpdateWrapper<AdminRole>().eq(AdminRole::getRoleId, id));
        //删除角色和权限的中间表 和本角色相关的数据
        roleMenuMapper.delete(new LambdaUpdateWrapper<RoleMenu>().eq(RoleMenu::getRoleId, id));
        //删除角色
        return roleMapper.deleteById(id);
    }


    @Override
    public int batchDeleteCascade(List<Long> ids) {
        ids.forEach(id->{
            //删除员工和角色的中间表 和本角色相关的数据
            adminRoleMapper.delete(new LambdaUpdateWrapper<AdminRole>().eq(AdminRole::getRoleId, id));
            //删除角色和权限的中间表 和本角色相关的数据
            roleMenuMapper.delete(new LambdaUpdateWrapper<RoleMenu>().eq(RoleMenu::getRoleId, id));
        });
        //删除角色
        return roleMapper.deleteBatchIds(ids);
    }

}
