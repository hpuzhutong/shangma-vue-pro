package com.zhu.sm.service;

import com.zhu.sm.common.page.PageBean;
import com.zhu.sm.dto.MenuDTO;
import com.zhu.sm.entity.Menu;
import com.zhu.sm.query.MenuQuery;
import com.zhu.sm.service.base.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/29 20:26
 * @className: MenuService
 * @description:
 */
@Service
public interface MenuService extends BaseService<Menu> {

    PageBean<MenuDTO> searchPage(MenuQuery menuQuery);



}
