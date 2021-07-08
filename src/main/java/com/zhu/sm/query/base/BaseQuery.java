package com.zhu.sm.query.base;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/23 20:18
 * @className: BaseQuery
 * @description: 用于基础查询  即每个查询对象都有的相同属性
 */

@Data
public class BaseQuery {

    private int currentPage = 1;
    private int pageSize = 8;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;


}
