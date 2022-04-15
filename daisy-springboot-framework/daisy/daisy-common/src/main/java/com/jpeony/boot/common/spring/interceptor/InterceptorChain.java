package com.jpeony.boot.common.spring.interceptor;

/**
 * 智能拦截器链, 能排序和添加数据
 *
 * @author yihonglei
 */
public interface InterceptorChain {
    /**
     * 执行拦截器
     *
     * @param context 上线文
     * @return true表示继续走后续逻辑; false表示不走后续逻辑，退出本次调用
     */
    boolean preHandle(String context);
}
