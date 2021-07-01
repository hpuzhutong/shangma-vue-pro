package com.zhu.sm.common.util;

import com.zhu.sm.common.exception.FormValidException;
import com.zhu.sm.common.http.AxiosStatus;
import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/5/29 14:14
 * 文件说明： 手动表单校验工具类
 */
public class FormValidUtils {
    private static Validator validator;

    //初始化校验器
    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 验证表单
     */
    public synchronized static <T> void valid(T t, Class<?>... clazz) {
        Set<ConstraintViolation<T>> validate = validator.validate(t, clazz);
        if (!CollectionUtils.isEmpty(validate)) {
            Map<String, String> errorMap = new ConcurrentHashMap<>();
            validate.forEach(tConstraintViolation -> {
                errorMap.put(tConstraintViolation.getPropertyPath().toString(), tConstraintViolation.getMessage());
            });
            throw new FormValidException(AxiosStatus.FORM_VALID_ERROR, errorMap);
        }
    }

    /**
     * 指定属性校验
     */
    public synchronized static <T> void valid(T t, String fieldName, Class<?>... clazz) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validateProperty(t, fieldName, clazz);
        if (!CollectionUtils.isEmpty(constraintViolations)) {
            Map<String, String> errorMap = new ConcurrentHashMap<>();
            constraintViolations.forEach(tConstraintViolation -> {
                errorMap.put(tConstraintViolation.getPropertyPath().toString(), tConstraintViolation.getMessage());
            });
            throw new FormValidException(AxiosStatus.FORM_VALID_ERROR, errorMap);
        }


    }
}
