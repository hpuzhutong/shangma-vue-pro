package com.zhu.sm.dto;

import com.zhu.sm.dto.base.BaseDTO;
import lombok.Data;

import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/29 20:21
 * @className: MenuDTO
 * @description:
 */

@Data
public class MenuDTO extends BaseDTO {

    private String menuTitle;
    private Long parentId;
    private Integer menuType;
    private Integer sort;
    private String menuRouter;
    private String menuIcon;
    private String componentPath;
    private String componentName;
    private String permSign;

    private List<MenuDTO> children;

}
