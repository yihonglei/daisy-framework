package com.lanhuigu.core.interceptor;

import com.lanhuigu.common.annotation.Intercept;
import com.lanhuigu.common.spring.interceptor.AbstractInterceptorAdapter;

/**
 * 黑名单拦截
 *
 * @author yihonglei
 * @date 2019/11/2 10:23 AM
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
