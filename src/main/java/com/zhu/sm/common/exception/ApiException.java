package com.zhu.sm.common.exception;

import com.zhu.sm.common.http.AxiosStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/27 17:02
 * @className: ApiException
 * @description:
 */

@Data
@AllArgsConstructor
public class ApiException extends RuntimeException{

    private AxiosStatus axiosStatus;
}
