package com.zhu.sm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.MenuDTO;
import com.zhu.sm.entity.Menu;
import com.zhu.sm.mapper.MenuMapper;
import com.zhu.sm.query.MenuQuery;
import com.zhu.sm.service.MenuService;
import com.zhu.sm.service.base.impl.BaseServiceImpl;
import com.zhu.sm.transfer.MenuTransfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 19:11
 * @className: BrandServiceImpl
 * @description:
 */

/*
    两个实体类的替换
 */

@Service
@Transactional
public class MenuServiceImpl extends BaseServiceImpl<Menu> implements MenuService {


    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private MenuTransfer menuTransfer;

    @Override
    public PageBean<MenuDTO> searchPage(MenuQuery menuQuery) {
        //拿到所有数据
        //查询所有一级分类
        List<Menu> rootMenus = menuMapper.selectList(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId,0L));
        PageInfo<Menu> pageInfo = new PageInfo<>(rootMenus);
        List<MenuDTO> rootDTOS = menuTransfer.toDTO(rootMenus);
        //非一级分类
        List<Menu> otherMenus = menuMapper.selectList(new QueryWrapper<Menu>().lambda().eq(Menu::getParentId, 0L));
        List<MenuDTO> otherDTOS = menuTransfer.toDTO(otherMenus);
        rootDTOS.forEach(menu -> getChildren(menu, otherDTOS));
        return PageBean.iniData(pageInfo.getTotal(),rootDTOS);

//        LambdaQueryWrapper<Menu> lqw = new LambdaQueryWrapper<>();
//
//        if (!StringUtils.isEmpty(menuQuery.getMenuTitle())){
//            lqw.like(Menu::getMenuTitle,menuQuery.getMenuTitle());
//        }
//        if (Objects.nonNull(menuQuery.getMenuType())){
//            lqw.eq(Menu::getMenuType,menuQuery.getMenuType());
//        }
//        if (Objects.nonNull(menuQuery.getStartTime()) && Objects.nonNull(menuQuery.getEndTime())){
//            lqw.between(Menu::getCreateTime,menuQuery.getStartTime(),menuQuery.getEndTime());
//        }
//
//        List<Menu> menus = menuMapper.selectList(lqw);
//
//        PageInfo<Menu> pageInfo = new PageInfo<>(menus);
//        List<MenuDTO> menuDTOS = menuTransfer.toDTO(menus);

//        return PageBean.iniData(pageInfo.getTotal(),menuDTOS);
    }


    //递归
    public void getChildren(MenuDTO menuDTO, List<MenuDTO> list) {
        List<MenuDTO> collect = list.stream().filter(menu1 -> menu1.getParentId().longValue() == menuDTO.getId()).collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            collect.forEach(menu2 -> getChildren(menu2, list));
        }
    }
}
