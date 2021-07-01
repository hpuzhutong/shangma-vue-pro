package com.zhu.sm.query;

import com.zhu.sm.query.base.BaseQuery;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.Objects;

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

    /**
     * 表示是不是查询
     * 四者同时为空 表示非查询
     */
    public boolean isQuery(){
       return  !(StringUtils.isEmpty(menuTitle) && Objects.isNull(menuType) && Objects.isNull(getStartTime()) && Objects.isNull(getEndTime()));
    }
}
