package com.zhu.sm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
//@EqualsAndHashCode  //重写equals和hashcode 在登录的时候返回权限
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
    //只能叫children  否者相关工具类不能使用
    private List<MenuDTO> children;

}
