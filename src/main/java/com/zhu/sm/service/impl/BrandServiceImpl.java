package com.zhu.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.PageInfo;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.BrandDTO;
import com.zhu.sm.entity.Brand;
import com.zhu.sm.mapper.BrandMapper;
import com.zhu.sm.query.BrandQuery;
import com.zhu.sm.service.BrandService;
import com.zhu.sm.service.base.impl.BaseServiceImpl;
import com.zhu.sm.transfer.BrandTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
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
public class BrandServiceImpl extends BaseServiceImpl<Brand> implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Autowired
    private BrandTransfer brandTransfer;

    @Override
    public PageBean<BrandDTO> searchPage(BrandQuery brandQuery) {
        LambdaQueryWrapper<Brand> lqw = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(brandQuery.getBrandName())) {
            lqw.like(Brand::getBrandName, brandQuery.getBrandName());
        }
        if (!StringUtils.isEmpty(brandQuery.getBrandDesc())) {
            lqw.like(Brand::getBrandDesc, brandQuery.getBrandDesc());
        }
        if (Objects.nonNull(brandQuery.getStartTime()) && Objects.nonNull(brandQuery.getEndTime())) {
            //品牌的入库时间要在表单传来的时间之内
            lqw.between(Brand::getCreateTime,brandQuery.getStartTime(),brandQuery.getEndTime());
        }

        //排序
        lqw.orderByDesc(Brand::getId);
        List<Brand> brands = brandMapper.selectList(lqw);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        //把几何中的brands转成brandDTO
        List<BrandDTO> brandDTOS = brandTransfer.toDTO(brands);

        return PageBean.iniData(pageInfo.getTotal(),brandDTOS);
    }
}
