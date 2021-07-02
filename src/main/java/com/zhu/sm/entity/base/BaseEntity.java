package com.zhu.sm.entity.base;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.zhu.sm.common.valid.group.AddGroup;
import com.zhu.sm.common.valid.group.UpdateGroup;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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

    @ExcelProperty(value = "id",index = 0)
    @NotNull(message = "修改时id不能为空",groups = {UpdateGroup.class})
    @Null(message = "添加时id必须为空",groups = {AddGroup.class})
    private Long id;

    @ExcelIgnore
    private Long createBy;

    @ExcelIgnore
    private LocalDateTime createTime;

    @ExcelIgnore
    private Long updateBy;

    @ExcelIgnore
    private LocalDateTime updateTime;


    /**
     * 添加数据
     *
     * 添加的时候没有id  修改的时候有id
     */
    public void setData() {
        if (id == null) {
            this.createBy = 1L;
            this.createTime = LocalDateTime.now();
        } else {
            this.createBy = 2L;
            this.createTime = LocalDateTime.now();
        }
    }

}
