package com.zhu.sm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/3 10:21
 * @className: Corn
 * @description:  定时类
 */

@Data
@TableName(value = "t_corn")
public class CornBean implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long cornId;

    private String cornExpress;

    private String cornDesc;
}
