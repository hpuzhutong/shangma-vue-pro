package com.zhu.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.entity.CornBean;
import com.zhu.sm.mapper.BrandMapper;
import com.zhu.sm.mapper.CornBeanMapper;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.service.BrandService;
import com.zhu.sm.service.CornBeanService;
import com.zhu.sm.service.base.impl.BaseServiceImpl;
import com.zhu.sm.transfer.BrandTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

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
public class CornBeanServiceImpl extends BaseServiceImpl<CornBean> implements CornBeanService {

    @Autowired
    private CornBeanMapper cornBeanMapper;

    @Override
    public int addCorm(CornBean cornBean) {
        return cornBeanMapper.insert(cornBean);
    }

    @Override
    public int updateCorn(CornBean cornBean) {
        return cornBeanMapper.updateById(cornBean);
    }
}
