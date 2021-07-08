package com.zhu.sm.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 18:57
 * @className: Brand
 * @description:
 */

/*
 *  实体类分层：
 *       1.负责查询时的参数接收相关的实体类  -> BaseEntity
 *       2.负责添加修改参数接收相关的实体类  ->
 *       3.返回前端需要的实体类
 * */

@Data
@TableName(value = "t_brand")

@ContentRowHeight(80)  //内容的行高
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

public class Brand extends BaseEntity {

    @ExcelProperty(value = "品牌名称")
    @NotBlank(message = "品牌名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String brandName;

    @ExcelProperty(value = "品牌网址")
    @NotBlank(message = "品牌网址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @URL(message = "必须是可访问的网址")
    private String brandSite;


    @NotBlank(message = "品牌描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String brandDesc;
    @ExcelProperty(value = "品牌描述")

    @ExcelIgnore

    @NotBlank(message = "品牌图标不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String brandLogo;
    @ExcelProperty(value = "品牌图标")
    private transient java.net.URL url;
}
