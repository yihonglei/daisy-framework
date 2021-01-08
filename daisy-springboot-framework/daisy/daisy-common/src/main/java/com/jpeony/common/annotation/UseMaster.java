package com.jpeony.common.annotation;

import java.lang.annotation.*;

/**
 * 强制使用主库
 *
 * @author yihonglei
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UseMaster {
}
