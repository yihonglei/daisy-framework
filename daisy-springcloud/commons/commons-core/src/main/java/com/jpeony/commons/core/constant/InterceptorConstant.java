package com.jpeony.commons.core.constant;

/**
 * 拦截
 *
 * @author yihonglei
 */
public interface InterceptorConstant {
    String NGINX = "/nginx.html";
    /**
     * header拦截器不需要拦截的请求
     */
    String[] EXCLUDE_HEADER = {NGINX};
    /**
     * token拦截器不需要拦截的请求
     */
    String[] EXCLUDE_TOKEN = {NGINX};
}
