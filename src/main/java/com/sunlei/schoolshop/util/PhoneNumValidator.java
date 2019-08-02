package com.sunlei.schoolshop.util;

import com.sunlei.schoolshop.Annotation.PhoneNum;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 孙磊
 * 手机号码验证工具类
 */
public class PhoneNumValidator implements ConstraintValidator<PhoneNum, String> {
    /**
     * 手机号的正则表达式.
     */
    private static Pattern pattern = Pattern.compile(
            "^0?(13[0-9]|14[0-9]|15[0-9]|16[0-9]|17[0-9]|18[0-9]|19[0-9])[0-9]{8}$");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        Matcher m = pattern.matcher(value);
        return m.matches();
    }

    @Override
    public void initialize(PhoneNum constraintAnnotation) {}
}
