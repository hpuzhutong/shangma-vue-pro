package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
public class AdminRole extends BaseEntity {


    private Long roleId;

    private Long adminId;


}
