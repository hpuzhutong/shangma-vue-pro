package com.zhu.sm.query;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zhu.sm.query.base.BaseQuery;
import lombok.Data;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/23 20:23
 * @className: BrandQuery
 * @description:
 */
@Data
public class AdminQuery extends BaseQuery {

    private String adminAccount;
    private String adminPhone;
    private Integer gender;
    //integer 在不传值的时候默认为 null  int会默认为0
    private String adminAddress;
}
