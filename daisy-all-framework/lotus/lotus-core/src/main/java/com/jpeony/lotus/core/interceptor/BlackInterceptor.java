package com.jpeony.lotus.core.interceptor;

import com.jpeony.lotus.common.annotation.Intercept;
import com.jpeony.lotus.common.spring.interceptor.AbstractInterceptorAdapter;

/**
 * 黑名单拦截
 *
 * @author yihonglei
 */
@Intercept(order = 2)
public class BlackInterceptor extends AbstractInterceptorAdapter {

    @Override
    protected boolean handle(String context) {
        logger.info("BlackInterceptor.handle");

        return true;
    }

    @Override
    protected boolean isSupport(String context) {
        return true;
    }
}
