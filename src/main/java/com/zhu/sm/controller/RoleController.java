package com.zhu.sm.controller;

import com.github.pagehelper.PageHelper;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.controller.base.BaseController;
import com.zhu.sm.dto.RoleDTO;
import com.zhu.sm.entity.Role;
import com.zhu.sm.query.RoleQuery;
import com.zhu.sm.service.RoleService;
import com.zhu.sm.transfer.RoleTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:18
 * @className: BrandController
 * @description:
 */
@RestController
@RequestMapping("role")

public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleTransfer roleTransfer;

    @GetMapping("searchPage")
    public AxiosResult<PageBean<RoleDTO>> searchPage(RoleQuery roleQuery) {
        PageHelper.startPage(roleQuery.getCurrentPage(), roleQuery.getPageSize());
        PageBean<RoleDTO> pageBean = roleService.searchPage(roleQuery);
        return AxiosResult.success(pageBean);
    }

    /**
     * 拿到所有角色
     */
    @GetMapping
    public AxiosResult<List<RoleDTO>> findAll() {
        List<Role> roleAll = roleService.findAll();
        return AxiosResult.success(roleTransfer.toDTO(roleAll));
    }

    @GetMapping("{id}")
    public AxiosResult<RoleDTO> findById(@PathVariable long id) {
        Role byId = roleService.findById(id);
        return AxiosResult.success(roleTransfer.toDTO(byId));
    }

    @PostMapping
    public AxiosResult addBrand(@Validated(AddGroup.class) @RequestBody Role role) {
        return toAxios(roleService.add(role));
    }

    /**
     * 删除角色
     */
    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable long id) {
        return toAxios(roleService.deleteCascade(id));
//        return toAxios(roleService.deleteById(id));
    }

    /**
     * 批量删除角色 以及和角色相关的权限
     */
    @DeleteMapping("batch/{ids}")
    public AxiosResult<Void> deleteById(@PathVariable List<Long> ids) {
        return toAxios(roleService.batchDeleteCascade(ids));
//        return toAxios(roleService.batchDelByIds(ids));
    }

    @PutMapping
    public AxiosResult<Void> updateById(@Validated(UpdateGroup.class) @RequestBody Role role) {
        return toAxios(roleService.update(role));
    }

    /**
     * 给角色赋予权限
     */
    @PostMapping("{roleId}/menu/{menuIds}")
    public AxiosResult<Void> setRoleMenu(@PathVariable Long roleId, @PathVariable List<Long> menuIds) {
        int row = roleService.setRoleMenu(roleId,menuIds);
        return toAxios(row);
    }

    /**
     * 获取角色的权限
     */
    @GetMapping("{roleId}/menu")
    public AxiosResult<List<Long>> getMenusByRoleId(@PathVariable Long roleId){
        List<Long> menuIds =  roleService.getMenusByRoleId(roleId);
        return AxiosResult.success(menuIds);
    }


}
