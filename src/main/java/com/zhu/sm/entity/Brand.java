package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.entity.base.BaseEntity;
import lombok.Data;

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
public class Brand  extends BaseEntity {

    private String brandName;
    private String brandSite;
    private String brandDesc;
    private String brandLogo;

}
