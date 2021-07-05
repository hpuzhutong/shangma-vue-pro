package com.zhu.sm.service;

import com.zhu.sm.entity.CornBean;
import com.zhu.sm.service.base.BaseService;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/3 11:12
 * @className: CornBeanService
 * @description:
 */
public interface CornBeanService extends BaseService<CornBean> {


    int addCorm(CornBean cornBean);

    int updateCorn(CornBean cornBean);
}
