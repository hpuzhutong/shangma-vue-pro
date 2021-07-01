package com.zhu.sm.common.util.anno;

import org.springframework.util.CollectionUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/1 10:20
 * @className: HandSomeTongHandler
 * @description:
 */
public class HandSomeTongHandler implements ConstraintValidator<HandSomeTong, Integer> {
    private List<Integer> list;

    @Override
    public void initialize(HandSomeTong constraintAnnotation) {
        int[] values = constraintAnnotation.values();
        list = CollectionUtils.arrayToList(values);
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return list.contains(value);
    }

}
