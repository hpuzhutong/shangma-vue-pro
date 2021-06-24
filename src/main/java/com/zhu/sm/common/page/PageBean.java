package com.zhu.sm.common.page;

import lombok.Data;

import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 20:40
 * @className: PageBean
 * @description:
 */
@Data
public class PageBean<T> {
    private long total;
    private List<T> data;

    public PageBean(long total, List<T> data) {
        this.total = total;
        this.data = data;
    }

    public static <T> PageBean<T> iniData(long total, List<T> data) {
        return new PageBean<T>(total, data);
    }
}
