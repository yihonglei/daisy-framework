package com.jpeony.boot.common.spring.interceptor;

/**
 * 智能拦截器链, 链条排序
 *
 * @author yihonglei
 */
public interface SmartInterceptorChain extends InterceptorChain {
    /**
     * 排序
     */
    void sort();
}
