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
public class BrandQuery extends BaseQuery {

    private String brandName;
    private String brandDesc;
}
