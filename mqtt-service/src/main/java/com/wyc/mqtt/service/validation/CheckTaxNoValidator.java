package com.wyc.mqtt.service.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 税号检查器
 *
 * @author guoyi
 * @date 2019/11/11 18:42
 */
public class CheckTaxNoValidator implements ConstraintValidator<CheckTaxNo, String> {

    /**
     * 检查入参是否符合税号规则
     *
     * @param s                          被检查的参数
     * @param constraintValidatorContext 上下文
     * @return 是否通过检查：true 通过；false 未通过
     */
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return false;
        }
        // 验证税号
        Pattern pattern = Pattern.compile("^[A-Z0-9]{15}$|^[A-Z0-9]{17}$|^[A-Z0-9]{18}$|^[A-Z0-9]{20}$");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}