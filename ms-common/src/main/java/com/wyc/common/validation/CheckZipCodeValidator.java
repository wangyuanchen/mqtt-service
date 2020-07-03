package com.wyc.common.validation;

import com.wyc.common.enumtype.ZipCodeEnum;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * zipCode检查器
 *
 * @author guoyi
 * @date 2019/11/20 18:42
 */
public class CheckZipCodeValidator implements ConstraintValidator<CheckZipCode, String> {

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
        boolean bool = false;
        // 验证ZipCode
        for (ZipCodeEnum zipCodeEnum : ZipCodeEnum.values()) {
            if (zipCodeEnum.getZipCode().equals(s)) {
                bool = true;
                break;
            }
        }
        return bool;
    }
}