package com.jpeony.boot.common.spring;

import com.alibaba.fastjson.JSON;
import com.jpeony.boot.common.constant.InterceptorConstant;
import com.jpeony.boot.common.constant.TraceConstant;
import com.jpeony.boot.common.utils.DecodeUtils;
import org.apache.commons.lang3.StringUtils;
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
        String uuid = UUID.randomUUID().toString().replace("-", "");
        MDC.put(TraceConstant.TRACE_KEY, uuid);
        // 日志中增加业务编号，比如订单号，要求订单号参数必须是orderNo
        MDC.put(TraceConstant.ORDER_NO, request.getParameter(TraceConstant.ORDER_NO));
        String ngTraceId = request.getHeader(TraceConstant.NG_TRACE_ID);
        if (StringUtils.isBlank(ngTraceId)) {
            ngTraceId = uuid;
        }
        MDC.put(TraceConstant.NG_TRACE_ID, ngTraceId);
        if (InterceptorConstant.NGINX.equals(request.getRequestURI())) {
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
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("api[{}]接口耗时:[{}]ms", request.getRequestURI(), THREAD_LOCAL.get().getTotalTimeMillis());
        MDC.remove(TraceConstant.TRACE_KEY);
        MDC.remove(TraceConstant.ORDER_NO);
        MDC.remove(TraceConstant.NG_TRACE_ID);
        THREAD_LOCAL.remove();
    }
}
