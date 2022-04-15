package com.jpeony.boot.common.spring.interceptor;

/**
 * 拦截器接口
 *
 * @author yihonglei
 */
public interface HandlerInterceptor {
    /**
     * 进行拦截
     *
     * @param context 上线文
     * @return false 中断请求, 业务处理中断; true, 链条继续往下走, 业务继续处理;
     */
    boolean preHandle(String context);
}
