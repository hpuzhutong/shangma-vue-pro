package com.zhu.sm.query;

import com.zhu.sm.query.base.BaseQuery;
import lombok.Data;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/23 20:23
 * @className: BrandQuery
 * @description:
 */
@Data
public class CronQuery extends BaseQuery {

    private Long cornId;

    private String cornExpress;

    private String cornDesc;
}
