package com.lanhuigu.common.constant;

/**
 * 拦截
 *
 * @author yihonglei
 * @date 2019/11/16 2:34 PM
 */
public final class InterceptorConstant {
    public final static String NGINX = "/nginx.html";
    /**
     * header拦截器不需要拦截的请求
     */
    public final static String[] EXCLUDE_HEADER = {NGINX};
    /**
     * token拦截器不需要拦截的请求
     */
    public final static String[] EXCLUDE_TOKEN = {NGINX};
}
