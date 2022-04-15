package com.jpeony.commons.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 拦截器注解，加了此注解才能使用
 *
 * @author yihonglei
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Intercept {
    /**
     * 排序字段，越小越靠前
     */
    int order() default 0;
}
