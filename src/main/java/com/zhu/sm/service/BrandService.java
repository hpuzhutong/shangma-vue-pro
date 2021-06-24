package com.zhu.sm.service;

import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.service.base.BaseService;


/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:04
 * @className: BrandService
 * @description:
 */
public interface BrandService extends BaseService<Brand> {

    /**
     * 条件分页查询
     */
    PageBean<BrandDTO> searchPage(BrandQuery brandQuery);
}
