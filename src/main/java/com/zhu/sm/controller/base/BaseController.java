package com.zhu.sm.controller.base;

import com.zhu.sm.common.http.AxiosResult;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/22 20:55
 * @className: BaseController
 * @description:
 */
public class BaseController {

    public AxiosResult<Void> toAxios(int row) {
        if (row > 0) {
            return AxiosResult.success();
        } else {
            return AxiosResult.error();
        }
    }
}
