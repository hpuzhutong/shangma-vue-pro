package com.zhu.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageInfo;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.dto.RoleDTO;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.entity.Role;
import com.zhu.sm.entity.RoleMenu;
import com.zhu.sm.mapper.BrandMapper;
import com.zhu.sm.mapper.RoleMapper;
import com.zhu.sm.mapper.RoleMenuMapper;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.query.RoleQuery;
import com.zhu.sm.service.BrandService;
import com.zhu.sm.service.RoleService;
import com.zhu.sm.service.base.impl.BaseServiceImpl;
import com.zhu.sm.transfer.BrandTransfer;
import com.zhu.sm.transfer.RoleTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private RoleMenuMapper roleMenuMapper;

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
        List<Long> collect = roleMenuMapper.selectList(new LambdaQueryWrapper<RoleMenu>().eq(RoleMenu::getMenuId, roleId))
                .stream().map(RoleMenu::getMenuId).collect(Collectors.toList());


        return null;
    }
}
