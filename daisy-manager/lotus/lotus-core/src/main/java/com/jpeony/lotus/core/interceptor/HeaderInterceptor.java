package com.jpeony.lotus.core.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.jpeony.lotus.common.constant.CommonConstant;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;

/**
 * header 信息处理
 */
public class HeaderInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static String MARK = "redMark";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        Map<String, String> headerMap = Maps.newHashMap();
        Enumeration<String> enumeration = request.getHeaderNames();
        if (enumeration != null) {
            while (enumeration.hasMoreElements()) {
                String key = enumeration.nextElement();
                headerMap.put(key, request.getHeader(key));
            }
        }
        logger.info("请求接口:{} requestHeader:{}", request.getRequestURI(), JSON.toJSONString(headerMap));
        String token = headerMap.get("token");
        HeaderBO headerBO = new HeaderBO();
        headerBO.setToken(token);
        request.setAttribute(CommonConstant.HEADER, headerBO);
        // TODO 装入用户信息
        return true;
    }
}
