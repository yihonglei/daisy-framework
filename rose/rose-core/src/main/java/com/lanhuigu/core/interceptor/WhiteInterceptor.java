package com.lanhuigu.core.interceptor;

import com.lanhuigu.common.annotation.Intercept;
import com.lanhuigu.common.spring.interceptor.AbstractInterceptorAdapter;

/**
 * 白名单拦截
 *
 * @author yihonglei
 * @date 2019/11/2 10:23 AM
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
