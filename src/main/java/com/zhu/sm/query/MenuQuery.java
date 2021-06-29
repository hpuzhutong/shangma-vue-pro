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
public class MenuQuery extends BaseQuery {

    private String menuTitle;
    private Integer menuType;
}
