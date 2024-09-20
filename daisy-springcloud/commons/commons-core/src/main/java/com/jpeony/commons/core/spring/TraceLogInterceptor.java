package com.jpeony.commons.core.spring;

import com.alibaba.fastjson.JSON;
import com.jpeony.commons.core.util.DecodeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.jpeony.commons.core.constant.InterceptorConstant.NGINX;
import static com.jpeony.commons.core.constant.TraceConstant.*;

/**
 * 日志跟踪
 *
 * @author yihonglei
 */
public class TraceLogInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private static ThreadLocal<StopWatch> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = MDC.get(TRACE_KEY);
        if(StringUtils.isBlank(traceId)){
            MDC.put(TRACE_KEY, getTraceId(request));
        }
        // 日志中增加业务编号，比如订单号，要求订单号参数必须是orderNo
        MDC.put(ORDER_NO, request.getParameter(ORDER_NO));
        if (NGINX.equals(request.getRequestURI())) {
            return true;
        }
        // 打印请求头
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            Map<String, String> headers = new HashMap<>(16);
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                headers.put(name, request.getHeader(name));
            }
            logger.info("url:[{}] 请求header:[{}]", request.getRequestURI(), JSON.toJSONString(headers));
        }

        // 打印所有入参
        Enumeration<String> names = request.getParameterNames();
        if (names != null) {
            Map<String, String> params = new HashMap<>(16);
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                params.put(name, DecodeUtils.decode(request.getParameter(name)));
            }
            logger.info("url:[{}] 请求参数:[{}]", request.getRequestURI(), JSON.toJSONString(params));
        }
        THREAD_LOCAL.set(new StopWatch());
        THREAD_LOCAL.get().start();
        return true;
    }

    private String getTraceId(HttpServletRequest request) {
        String traceId = request.getHeader(NG_TRACE_ID);
        if(StringUtils.isNotBlank(traceId)){
            return traceId;
        }
        traceId = request.getHeader(TRACE_KEY);
        if(StringUtils.isNotBlank(traceId)){
            return traceId;
        }
        traceId = TraceContext.traceId();
        if(StringUtils.isNotBlank(traceId)){
            return traceId;
        }
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        THREAD_LOCAL.get().stop();
        logger.info("api[{}]接口耗时:[{}]ms", request.getRequestURI(), THREAD_LOCAL.get().getTotalTimeMillis());
        MDC.remove(TRACE_KEY);
        MDC.remove(ORDER_NO);
        THREAD_LOCAL.remove();
    }
}
