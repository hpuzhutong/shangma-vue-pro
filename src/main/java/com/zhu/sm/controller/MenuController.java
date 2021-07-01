package com.zhu.sm.controller;

import com.github.pagehelper.PageHelper;
import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.common.util.FormValidUtils;
import com.zhu.sm.common.valid.group.*;
import com.zhu.sm.controller.base.BaseController;
import com.zhu.sm.dto.MenuDTO;
import com.zhu.sm.entity.Menu;
import com.zhu.sm.query.MenuQuery;
import com.zhu.sm.service.MenuService;
import com.zhu.sm.transfer.MenuTransfer;
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
@RequestMapping("menu")

public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuTransfer menuTransfer;

    @GetMapping("searchPage")
    public AxiosResult<PageBean<MenuDTO>> searchPage(MenuQuery menuQuery) {
        PageHelper.startPage(menuQuery.getCurrentPage(), menuQuery.getPageSize());
        PageBean<MenuDTO> pageBean = menuService.searchPage(menuQuery);
        return AxiosResult.success(pageBean);
    }

//    @GetMapping
//    public AxiosResult<List<MenuDTO>> findAll() {
//        return AxiosResult.success(menuService.findAll());
//    }

    @GetMapping("{id}")
    public AxiosResult<MenuDTO> findById(@PathVariable long id) {
        Menu menu = menuService.findById(id);
        return AxiosResult.success(menuTransfer.toDTO(menu));
    }

    @PostMapping
    public AxiosResult addMenu(@Validated({AddGroup.class}) @RequestBody Menu menu) {
        Integer menuType = menu.getMenuType();
        if (menuType == 1) {
            //判断目录相关内容
            FormValidUtils.valid(menu,DirectoryGroup.class);
        }
        if (menuType == 2) {
            //判断菜单相关内容
            FormValidUtils.valid(menu, MenuGroup.class);
        }
        if (menuType == 3) {
            //判断按钮相关内容
            FormValidUtils.valid(menu,BtnGroup.class);
        }
        return toAxios(menuService.add(menu));
    }


    /**
     * 删除一个和级联删除
     */
    @DeleteMapping("{id}")
    public AxiosResult<Void> deleteById(@PathVariable long id) {
        int row = menuService.cascadeDeleteChildren(id);
        return toAxios(row);
    }


    @PutMapping
    public AxiosResult<Void> updateById(@Validated(UpdateGroup.class) @RequestBody Menu menu) {
        Integer menuType = menu.getMenuType();
        if (menuType == 1) {
            //判断目录相关内容
            FormValidUtils.valid(menu,DirectoryGroup.class);
        }
        if (menuType == 2) {
            //判断菜单相关内容
            FormValidUtils.valid(menu, MenuGroup.class);
        }
        if (menuType == 3) {
            //判断按钮相关内容
            FormValidUtils.valid(menu,BtnGroup.class);
        }
        return toAxios(menuService.update(menu));
    }

    /**
     * 获得所有的权限
     * 展示在select框中
     */
    @GetMapping("getTreeData")
    public AxiosResult<List<MenuDTO>> getSelectTreeData() {
        List<MenuDTO> list = menuService.getTreeData();
        return AxiosResult.success(list);
    }


}
