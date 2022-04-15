package com.jpeony.boot.common.logback;

import com.jpeony.boot.common.constant.TraceConstant;
import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

/**
 * MDC中存放的记录，便于查询日志
 *
 * @author yihonglei
 */
@Data
public class LogTraceInfo implements Serializable {
    private static final long serialVersionUID = 3697722045461880439L;

    /**
     * 请求traceId
     */
    private String traceId;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * ngTraceId
     */
    private String ngTraceId;

    public LogTraceInfo(String traceId, String orderNo, String ngTraceId) {
        this.traceId = traceId;
        this.orderNo = orderNo;
        this.ngTraceId = ngTraceId;
    }

    /**
     * 基于当前MDC创建item
     */
    public static LogTraceInfo createByCurrentMDC() {
        return new LogTraceInfo(MDC.get(TraceConstant.TRACE_KEY), MDC.get(TraceConstant.ORDER_NO), MDC.get(TraceConstant.NG_TRACE_ID));
    }

    /**
     * 往当前线程的MDC中放值
     */
    public void putAll() {
        MDC.put(TraceConstant.TRACE_KEY, getTraceId());
        MDC.put(TraceConstant.ORDER_NO, getOrderNo());
        MDC.put(TraceConstant.NG_TRACE_ID, getNgTraceId());
    }

    /**
     * 移除MDC中的值
     */
    public void removeAll() {
        MDC.remove(TraceConstant.TRACE_KEY);
        MDC.remove(TraceConstant.ORDER_NO);
        MDC.remove(TraceConstant.NG_TRACE_ID);
    }
}
