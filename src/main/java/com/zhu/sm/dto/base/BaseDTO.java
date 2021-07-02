package com.zhu.sm.dto.base;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/24 12:57
 * @className: BaseDTO
 * @description:
 */

/*同意返回前段数据格式的基类*/
@Data
public class BaseDTO {


    private Long id;

    @JsonIgnore   //表示转json的时候忽略这个属性
    private Long createBy;

    @JsonIgnore
    private Long updateBy;

    @JsonIgnore
    private LocalDateTime createTime;

    @JsonIgnore
    private LocalDateTime updateTime;

}
