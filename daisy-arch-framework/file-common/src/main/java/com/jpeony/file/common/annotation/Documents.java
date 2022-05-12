package com.jpeony.file.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 多种文档类型
 *
 * @author yihonglei
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Documents {

    /**
     * 多种文件类型
     *
     * @return
     */
    Document[] docs() default {};
}
