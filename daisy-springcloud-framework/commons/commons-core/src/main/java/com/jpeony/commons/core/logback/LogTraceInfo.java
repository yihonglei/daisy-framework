package com.jpeony.commons.core.logback;

import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

import static com.jpeony.commons.core.constant.TraceConstant.*;


/**
 * MDC 中存放的记录，便于查询日志
 *
 * @author yihonglei
 */
@Data
public class LogTraceInfo implements Serializable {
    private static final long serialVersionUID = 3697722045461880439L;

    /**
     * 请求 traceId
     */
    private String traceId;
    /**
     * 业务追踪号（订单号，手机号，用户id等）
     */
    private String uniqueNo;
    /**
     * 请求 ngTraceId
     */
    private String ngTraceId;

    public LogTraceInfo(String traceId, String uniqueNo, String ngTraceId) {
        this.traceId = traceId;
        this.uniqueNo = uniqueNo;
        this.ngTraceId = ngTraceId;
    }

    /**
     * 基于当前MDC创建item
     */
    public static LogTraceInfo createByCurrentMDC() {
        return new LogTraceInfo(MDC.get(TRACE_ID), MDC.get(UNIQUE_NO), MDC.get(NG_TRACE_ID));
    }

    /**
     * 往当前线程的 MDC 中放值
     */
    public void putAll() {
        MDC.put(TRACE_ID, getTraceId());
        MDC.put(UNIQUE_NO, getUniqueNo());
        MDC.put(NG_TRACE_ID, getNgTraceId());
    }

    /**
     * 移除 MDC 中的值
     */
    public void removeAll() {
        MDC.remove(TRACE_ID);
        MDC.remove(UNIQUE_NO);
        MDC.remove(NG_TRACE_ID);
    }
}
