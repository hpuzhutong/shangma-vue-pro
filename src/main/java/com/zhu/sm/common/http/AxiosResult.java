package com.zhu.sm.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 20:22
 * @className: AxiosResult
 * @description:
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)  //data为null不携带data至前端
public class AxiosResult<T> {

    private int status;
    private String message;
    private T data;

    private AxiosResult(AxiosStatus axiosStatus, T t) {
        this.status = axiosStatus.getStatus();
        this.message = axiosStatus.getMessage();
        this.data = t;
    }

    private static <T> AxiosResult<T> getInstance(AxiosStatus axiosStatus, T t) {
        return new AxiosResult<T>(axiosStatus, t);
    }

    /**
     * 成功的方法
     */
    public static <T> AxiosResult<T> success() {
        return getInstance(AxiosStatus.OK, null);
    }

    /**
     * 成功的方法  携带数据
     */
    public static <T> AxiosResult<T> success(T t) {
        return getInstance(AxiosStatus.OK, t);
    }

    /**
     * 成功的方法  状态码
     */
    public static <T> AxiosResult<T> success(AxiosStatus axiosStatus) {
        return getInstance(axiosStatus, null);
    }


    /**
     * 失败的方法
     */
    public static <T> AxiosResult<T> error() {
        return getInstance(AxiosStatus.ERROR, null);
    }

    /**
     * 失败的方法  携带数据
     */
    public static <T> AxiosResult<T> error(T t) {
        return getInstance(AxiosStatus.ERROR, t);
    }

    /**
     * 失败的方法 状态码
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus) {
        return getInstance(axiosStatus, null);
    }

    /**
     * 失败的方法 状态码+数据
     */
    public static <T> AxiosResult<T> error(AxiosStatus axiosStatus, T t) {
        return getInstance(axiosStatus, t);
    }
}
