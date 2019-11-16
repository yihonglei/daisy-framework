package com.lanhuigu.common.spring;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.lanhuigu.common.util.DecodeUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Map;
import java.util.UUID;

import static com.lanhuigu.common.constant.InterceptorConstant.NGINX;
import static com.lanhuigu.common.constant.TraceConstant.NG_TRACE_ID;
import static com.lanhuigu.common.constant.TraceConstant.TRACE_KEY;

/**
 * 日志跟踪
 *
 * @author yihonglei
 * @date 2019/11/16 2:28 PM
 */
public class TraceLogInterceptor extends HandlerInterceptorAdapter {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static ThreadLocal<StopWatch> THREAD_LOCAL = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String traceId = MDC.get(TRACE_KEY);
        if (StringUtils.isBlank(traceId)) {
            traceId = UUID.randomUUID().toString().replace("-", "");
            MDC.put(TRACE_KEY, traceId);
        }
        String ngTraceId = MDC.get(NG_TRACE_ID);
        if (StringUtils.isBlank(ngTraceId)) {
            ngTraceId = request.getHeader(NG_TRACE_ID);
            if (StringUtils.isBlank(ngTraceId)) {
                ngTraceId = traceId;
            }
            MDC.put(NG_TRACE_ID, ngTraceId);
        }
        if (NGINX.equals(request.getRequestURI())) {
            return true;
        }

        //打印所有入参
        Enumeration<String> names = request.getParameterNames();
        if (names != null) {
            Map<String, String> params = Maps.newHashMap();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                params.put(name, DecodeUtil.decode(request.getParameter(name)));
            }
            logger.info("url:[{}] 请求参数:[{}]", request.getRequestURI(), JSON.toJSONString(params));
        }
        THREAD_LOCAL.set(new StopWatch());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("api[{}]接口耗时:[{}]ms", request.getRequestURI(), THREAD_LOCAL.get().getTotalTimeMillis());
        MDC.remove(TRACE_KEY);
        MDC.remove(NG_TRACE_ID);
        THREAD_LOCAL.remove();
    }
}
