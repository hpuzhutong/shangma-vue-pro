package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author HandSome_tong
 * @since 2021-06-25
 */
@Data
@TableName("t_admin")
public class Admin extends BaseEntity {

    @TableField(value = "admin_account")
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAccount;

    @NotBlank(message = "姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminName;

    private Integer gender;

    @NotBlank(message = "手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$",message = "手机号不符合规则")
    private String adminPhone;

    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$",message = "邮箱不合法")
    private String adminEmail;

    @NotBlank(message = "身份证号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",message = "身份证号不合法")
    private String adminCode;

    private String adminAvatar;

    private String adminPassword;

    private Boolean isActive;

    private Boolean isAdmin;

    @NotNull(message = "薪资不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Double adminSalary;

    @NotBlank(message = "地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAddress;


}
