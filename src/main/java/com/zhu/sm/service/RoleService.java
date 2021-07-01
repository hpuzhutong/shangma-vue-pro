package com.zhu.sm.service;

import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.dto.RoleDTO;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.entity.Role;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.query.RoleQuery;
import com.zhu.sm.service.base.BaseService;

import java.util.List;


/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:04
 * @className: BrandService
 * @description:
 */
public interface RoleService extends BaseService<Role> {

    /**
     * 条件分页查询
     */
    PageBean<RoleDTO> searchPage(RoleQuery roleQuery);

    int setRoleMenu(Long roleId, List<Long> menuIds);

    List<Long> getMenusByRoleId(Long roleId);
}
