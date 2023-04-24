package com.jpeony.boot.core.interceptor;

import com.jpeony.boot.common.annotation.Intercept;
import com.jpeony.boot.common.spring.interceptor.AbstractInterceptorAdapter;

/**
 * 白名单拦截
 *
 * @author yihonglei
 */
@Intercept(order = 1)
public class WhiteInterceptor extends AbstractInterceptorAdapter {
    
    @Override
    protected boolean handle(String context) {
        logger.info("WhiteInterceptor.handle");

        return true;
    }

    @Override
    protected boolean isSupport(String context) {
        return true;
    }
}
