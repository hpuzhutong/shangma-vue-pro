package com.zhu.sm.service;

import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.AdminDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.query.AdminQuery;
import com.zhu.sm.service.base.BaseService;

import java.util.List;


/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:04
 * @className: BrandService
 * @description:
 */
public interface AdminService extends BaseService<Admin> {

    /**
     * 条件分页查询
     */
    PageBean<AdminDTO> searchPage(AdminQuery adminQuery);

    int findByAccount(String adminAccount);

    /**
     * 判断是否有这个管理员在
     * @param admin
     * @return
     */
    boolean hasAccountEmailPhone(Admin admin);

    /**
     * 添加员工和员工的角色
     *
     * @param admin
     * @return
     */
    int addAdminAndAdminRole(Admin admin);

    /**
     * 通过id获取管理员的角色于信息
     * @param id
     * @return
     */
    AdminDTO getAdminAndRoleByAdminId(Long id);

    /**
     * 修改员工以及他的角色
     *
     * @param admin
     * @return
     */
    int updateAdminAndRole(Admin admin);

    /**
     * 删除员工的信息
     * @param id
     * @return
     */
    int deleteAdminAndRoleByAdminId(Long id);

    /**
     * 批量删除用户以及他的角色
     * @param ids
     * @return
     */
    int batchDeleteAdminAndRolesByAdminIds(List<Long> ids);

    Admin getAdminByAccount(String account);

    /**
     * 获得用户的所有角色
     * @param adminId
     * @return
     */
    List<Long> getRolesByAdminId(Long adminId);
}
