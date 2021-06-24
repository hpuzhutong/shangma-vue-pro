package com.zhu.sm.common.http;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 20:22
 * @className: AxiosStatus
 * @description:
 */
@Getter
@AllArgsConstructor
public enum  AxiosStatus {
    OK(2000,"操作成功"),
    ERROR(4000,"操作失败")
    ;

    private int status;
    private String message;
}
