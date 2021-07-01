package com.zhu.sm.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.common.util.anno.HandSomeTong;
import com.zhu.sm.common.valid.group.*;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author HandSome_tong
 * @since 2021-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_menu")
public class Menu extends BaseEntity {

    @NotBlank(message = "权限标题不能为空", groups = {MenuGroup.class, DirectoryGroup.class})
    private String menuTitle;

    @NotNull(message = "父级id不能为空", groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    private Long parentId;

    @NotNull(message = "权限类型不能为空", groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    @HandSomeTong(message = "权限类型不能为空", values = {1, 2, 3}, groups = {AddGroup.class, UpdateGroup.class, DirectoryGroup.class, BtnGroup.class})
    private Integer menuType;

    @NotNull(message = "权限排序不能为空", groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    private Integer sort;

    @NotBlank(message = "路由地址不能为空", groups = {MenuGroup.class, AddGroup.class})
    private String menuRouter;

    @NotBlank(message = "菜单图标不能为空", groups = {MenuGroup.class, DirectoryGroup.class})
    private String menuIcon;

    @NotBlank(message = "组件地址不能为空", groups = {MenuGroup.class, AddGroup.class})
    private String componentPath;

    @NotBlank(message = "组件名称不能为空", groups = {MenuGroup.class, AddGroup.class})
    private String componentName;

    @NotBlank(message = "权限标识不能为空", groups = {MenuGroup.class, DirectoryGroup.class, BtnGroup.class})
    private String permSign;


}
