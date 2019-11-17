package com.lanhuigu.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 拦截器注解,加了此注解才能使用
 *
 * @author yihonglei
 * @date 2019/11/2 10:10 AM
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Intercept {
    /**
     * 排序字段,越小越靠前
     */
    int order() default 0;
}
