package com.zhu.sm.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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

    /**
     * 权限名称
     */
    private String menuTitle;

    /**
     * 权限父亲id  如果是第一级 父id=0
     */
    private Long parentId;

    /**
     * 1表示目录  2表示菜单  3表示按钮
     */
    private Integer menuType;

    /**
     * 权限排序
     */
    private Integer sort;

    /**
     * 路由地址
     */
    private String menuRouter;

    /**
     * 菜单图标
     */
    private String menuIcon;

    /**
     * 组件地址
     */
    private String componentPath;

    /**
     * 组件名称
     */
    private String componentName;

    /**
     * 权限标识
     */
    private String permSign;



}
