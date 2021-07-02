package com.zhu.sm.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.common.util.anno.HandSomeTong;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;

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

@ContentRowHeight(150)  //内容的行高
@HeadRowHeight(40)  //头部行高
@ColumnWidth(30)  //宽
//头部样式  水平垂直居中
@HeadStyle(horizontalAlignment = HorizontalAlignment.CENTER,verticalAlignment = VerticalAlignment.CENTER)
// 头字体设置成20
@HeadFontStyle(fontHeightInPoints = 20)
//内容样式
@ContentStyle(fillForegroundColor = 17,horizontalAlignment = HorizontalAlignment.CENTER,verticalAlignment = VerticalAlignment.CENTER)
// 内容字体设置成20
@ContentFontStyle(fontHeightInPoints = 20,fontName = "华文行楷")
public class Admin extends BaseEntity {

    @ExcelProperty(value = "员工账号")
    @TableField(value = "admin_account")
    @NotBlank(message = "用户名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAccount;

    @ExcelProperty(value = "员工姓名")
    @NotBlank(message = "姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminName;

    @ExcelIgnore
    @HandSomeTong(values = {0,1})
    private Integer gender;
    @ExcelProperty(value = "员工性别")
    private transient String sex;

    @ExcelProperty(value = "员工手机号")
    @NotBlank(message = "手机号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$",message = "手机号不符合规则")
    private String adminPhone;

    @ExcelProperty(value = "员工邮箱")
    @NotBlank(message = "邮箱不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[a-z\\d]+(\\.[a-z\\d]+)*@([\\da-z](-[\\da-z])?)+(\\.{1,2}[a-z]+)+$",message = "邮箱不合法")
    private String adminEmail;

    @ExcelProperty(value = "员工身份证")
    @NotBlank(message = "身份证号不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp = "^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$",message = "身份证号不合法")
    private String adminCode;

    @ExcelIgnore
    private String adminAvatar;
    @ExcelProperty(value = "图像")
    private transient URL url;

    @ExcelIgnore
    private String adminPassword;

    @ExcelIgnore
    private Boolean isActive;

    @ExcelIgnore
    private Boolean isAdmin;

    @ExcelProperty(value = "员工薪资")
    @NotNull(message = "薪资不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private Double adminSalary;

    @ExcelProperty(value = "员工地址")
    @NotBlank(message = "地址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String adminAddress;

    @ExcelIgnore
    private transient List<Long> roleIds;


}
