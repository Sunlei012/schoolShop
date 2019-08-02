package com.sunlei.schoolshop.Annotation;

import com.sunlei.schoolshop.util.PhoneNumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author 孙磊
 * 手机号码验证注解
 */
@Target( {
        METHOD,
        FIELD,
        ANNOTATION_TYPE,
        CONSTRUCTOR,
        PARAMETER
})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneNumValidator.class})
public @interface PhoneNum {

    String regexp() default "";

    String message() default "手机号码格式不正确";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
