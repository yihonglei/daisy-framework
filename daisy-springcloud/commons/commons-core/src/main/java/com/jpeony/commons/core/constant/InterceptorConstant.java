package com.jpeony.commons.core.constant;

/**
 * 拦截
 *
 * @author yihonglei
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
