package com.zhu.sm.entity.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 20:18
 * @className: BaseEntity
 * @description:
 */

@Data
public class BaseEntity implements Serializable {

@TableId(type = IdType.AUTO)
    private long id;
    private long createBy;
    private LocalDateTime createTime;
    private long updateBy;
    private LocalDateTime updateTime;
}
