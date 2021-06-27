package com.zhu.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.AdminDTO;
import com.zhu.sm.entity.Admin;
import com.zhu.sm.mapper.AdminMapper;
import com.zhu.sm.query.AdminQuery;
import com.zhu.sm.service.AdminService;
import com.zhu.sm.service.base.impl.BaseServiceImpl;
import com.zhu.sm.transfer.AdminTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:11
 * @className: AdminServiceImpl
 * @description:
 */

/*
    两个实体类的替换
        b
 */

@Service
@Transactional
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminTransfer adminTransfer;

    @Override
    public PageBean<AdminDTO> searchPage(AdminQuery adminQuery) {
        LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(adminQuery.getAdminAccount())) {
            lqw.like(Admin::getAdminAccount, adminQuery.getAdminAccount());
        }
        if (!StringUtils.isEmpty(adminQuery.getAdminPhone())) {
            lqw.like(Admin::getAdminPhone, adminQuery.getAdminPhone());
        }
        if (!StringUtils.isEmpty(adminQuery.getAdminAddress())) {
            lqw.like(Admin::getAdminAddress, adminQuery.getAdminAddress());
        }
        if (Objects.nonNull(adminQuery.getGender())) {
            lqw.like(Admin::getGender, adminQuery.getGender());
        }
        if (Objects.nonNull(adminQuery.getStartTime()) && Objects.nonNull(adminQuery.getEndTime())) {
            lqw.between(Admin::getCreateTime, adminQuery.getStartTime(), adminQuery.getEndTime());
        }

        List<Admin> admins = adminMapper.selectList(lqw);
        PageInfo<Admin> pageInfo = new PageInfo<>(admins);
        List<AdminDTO> adminDTOS = adminTransfer.toDTO(admins);
        return PageBean.iniData(pageInfo.getTotal(), adminDTOS);

    }

    @Override
    public int findByAccount(String adminAccount) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper();
        queryWrapper.eq("admin_account", adminAccount);
        Admin admin = adminMapper.selectOne(queryWrapper);
        if (admin == null) {
            return 0;
        }
        return 1;
    }

    @Override
    public boolean hasAccountEmailPhone(Admin admin) {
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getAdminAccount, admin.getAdminAccount()).or().eq(Admin::getAdminEmail, admin.getAdminEmail()).or().eq(Admin::getAdminPhone, admin.getAdminPhone());
        Integer integer = adminMapper.selectCount(lambdaQueryWrapper);
        return integer > 0;
    }
}
