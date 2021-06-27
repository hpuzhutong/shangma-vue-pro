package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author HandSome_tong
 * @since 2021-06-25
 */
@Data
@TableName("t_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @NotBlank(message = "角色不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDesc;



}
