package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 18:57
 * @className: Brand
 * @description:
 */

/*
 *  实体类分层：
 *       1.负责查询时的参数接收相关的实体类  -> BaseEntity
 *       2.负责添加修改参数接收相关的实体类
 *       3.返回前端需要的实体类
 * */

@Data
@TableName(value = "t_brand")
public class Brand extends BaseEntity {

    @NotBlank(message = "品牌名称不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String brandName;

    @NotBlank(message = "品牌网址不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @URL(message = "必须是可访问的网址")
    private String brandSite;

    @NotBlank(message = "品牌描述不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String brandDesc;

    @NotBlank(message = "品牌图标不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String brandLogo;

}
