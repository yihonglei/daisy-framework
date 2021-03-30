package com.jpeony.pay.validatorextend;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 支付渠道校验注解
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {PayChannelValidator.class})
public @interface PayChannel {

    String message() default "支付渠道不合法";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
