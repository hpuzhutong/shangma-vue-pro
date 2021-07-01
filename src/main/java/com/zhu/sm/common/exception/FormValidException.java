package com.zhu.sm.common.exception;

import com.zhu.sm.common.http.AxiosStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/1 11:57
 * @className: FormValidException
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormValidException extends RuntimeException {


    private AxiosStatus axiosStatus;

    private Map<String, String> map;

}
