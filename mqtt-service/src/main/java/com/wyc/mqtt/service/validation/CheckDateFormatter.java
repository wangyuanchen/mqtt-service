package com.wyc.mqtt.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: wangyuanchen
 * @date: 2019-11-13 10:12
 * @description:
 */
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckDateVaildator.class)
@Documented
public @interface CheckDateFormatter {
    String message() default "时间戳不能为空";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    CaseMode value();
}
