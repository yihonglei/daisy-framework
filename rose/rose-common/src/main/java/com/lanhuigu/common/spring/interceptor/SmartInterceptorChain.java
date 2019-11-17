package com.lanhuigu.common.spring.interceptor;

/**
 * 智能拦截器链, 链条排序
 *
 * @author yihonglei
 * @date 2019/11/2 10:05 AM
 */
public interface SmartInterceptorChain extends InterceptorChain {
    /**
     * 排序
     */
    void sort();
}
