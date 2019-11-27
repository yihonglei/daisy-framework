package com.lanhuigu.core.interceptor;

import com.lanhuigu.common.annotation.Intercept;
import com.lanhuigu.common.spring.interceptor.AbstractInterceptorAdapter;

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
