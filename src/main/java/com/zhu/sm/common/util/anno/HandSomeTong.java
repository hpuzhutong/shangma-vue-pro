package com.zhu.sm.common.util.anno;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


/**
 * @anthor: HandSome_ZTon
 * @date: 2021/7/1 10:08
 * @className: HandSomeTong
 * @description:
 */
@Target(FIELD)
@Retention(RUNTIME)
//元注解 ：  修饰注解的注解  @Target指定注解的作用范围 @Retention 指定注解的生效时机\
//指定使用哪个处理器 取处理是否满足规则
@Constraint(validatedBy = {HandSomeTongHandler.class})
public @interface HandSomeTong {

    String message() default "{javax.validation.constraints.NotNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int[] values() default {};
}
