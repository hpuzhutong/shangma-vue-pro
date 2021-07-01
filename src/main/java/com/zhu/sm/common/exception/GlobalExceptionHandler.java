package com.zhu.sm.common.exception;

import com.zhu.sm.common.http.AxiosResult;
import com.zhu.sm.common.http.AxiosStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/6/25 10:07
 * @className: GlobalException
 * @description: 全局异常处理类
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(ApiException.class)
//    public AxiosResult<Void> handlerFormValidException(ApiException e){
//        return AxiosResult.error(e.getAxiosStatus());
//    }


    @ExceptionHandler(Throwable.class)
    public AxiosResult<Void> handlerThrowable(Throwable e){
        AxiosStatus uploadError = AxiosStatus.MENU_VALID_ERROR;
        uploadError.setMessage(e.getMessage());
        return AxiosResult.error(uploadError);
    }


    @ExceptionHandler(FormValidException.class)
    public AxiosResult<Map<String, String>> handlerFormValidException(FormValidException e){
        AxiosStatus axiosStatus = e.getAxiosStatus();
        return AxiosResult.error(axiosStatus,e.getMap());
    }


    /**
     * 处理表单检验异常
     */
    //一条一条的返回错误信息
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public AxiosResult<String> handlerFormValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        AxiosStatus formValidError = AxiosStatus.FORM_VALID_ERROR;
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if (!CollectionUtils.isEmpty(fieldErrors)) {
            FieldError fieldError = fieldErrors.get(0);
            String defaultMessage = fieldError.getDefaultMessage();
            formValidError.setMessage(defaultMessage);
            System.out.println(e);
            return AxiosResult.error(formValidError);
        }
        formValidError.setMessage("表单校验错误");
        System.out.println(e);
        return AxiosResult.error(formValidError);
    }





}
