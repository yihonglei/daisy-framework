package com.lanhuigu.common.spring.interceptor;

/**
 * 拦截器接口
 *
 * @author yihonglei
 * @date 2019/11/2 9:59 AM
 */
public interface HandlerInterceptor {
    /**
     * 进行拦截
     *
     * @param context 上线文
     * @return false中断请求, 业务处理中断; true, 链条继续往下走, 业务继续处理;
     */
    boolean preHandle(String context);
}
