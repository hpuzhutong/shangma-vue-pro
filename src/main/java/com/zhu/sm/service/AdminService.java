package com.zhu.sm.service;

import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.AdminDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.query.AdminQuery;
import com.zhu.sm.service.base.BaseService;


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

    boolean hasAccountEmailPhone(Admin admin);
}
