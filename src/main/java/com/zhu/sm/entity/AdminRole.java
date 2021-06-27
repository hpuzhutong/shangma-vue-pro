package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.entity.base.BaseEntity;
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
 * @since 2021-06-25
 */
@Data
@TableName("t_admin_role")
@AllArgsConstructor
@NoArgsConstructor
public class AdminRole implements Serializable  {


    private Long roleId;

    private Long adminId;


}
