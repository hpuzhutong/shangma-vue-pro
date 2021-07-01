package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
@TableName("t_role_menu")
@AllArgsConstructor
@NoArgsConstructor
public class RoleMenu implements Serializable {

    private Long roleId;

    private Long menuId;


}
