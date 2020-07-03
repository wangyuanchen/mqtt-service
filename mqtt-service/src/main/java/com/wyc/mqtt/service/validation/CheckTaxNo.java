package com.wyc.mqtt.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: guoyi
 * @Date: 2019/11/11 18:37
 * @Description:
 */
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckTaxNoValidator.class)
@Documented
public @interface CheckTaxNo {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    CaseMode value();
}