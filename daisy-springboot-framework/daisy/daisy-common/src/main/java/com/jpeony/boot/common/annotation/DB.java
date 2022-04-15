package com.jpeony.boot.common.annotation;

import java.lang.annotation.*;

/**
 * 修饰 Mapper 接口，拦截指定数据源
 *
 * @author yihonglei
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DB {
    /**
     * 数据源名称
     */
    String name() default "";
}
