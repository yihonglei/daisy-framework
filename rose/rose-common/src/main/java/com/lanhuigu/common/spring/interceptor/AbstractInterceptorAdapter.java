package com.lanhuigu.common.spring.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 拦截器执行适配
 *
 * @author yihonglei
 * @date 2019/11/2 10:01 AM
 */
public abstract class AbstractInterceptorAdapter implements HandlerInterceptor {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(String context) {
        if (!isSupport(context)) {
            return true;
        }

        return handle(context);
    }

    /**
     * 业务处理
     *
     * @param context 上下文
     * @return 处理成功或失败
     */
    protected abstract boolean handle(String context);

    /**
     * 是否启用拦截器
     *
     * @param context 上下文
     * @return true表示启用拦截器; false表示不启用,跳过本拦截器,继续后续拦截器处理
     */
    protected abstract boolean isSupport(String context);
}
