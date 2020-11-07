package com.jpeony.common.concurrent;

import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

import static com.jpeony.common.constant.TraceConstant.*;

/**
 * MDC中存放的记录，便于查询日志
 *
 * @author yihonglei
 */
@Data
public class TraceItem implements Serializable {
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

    public TraceItem(String traceId, String orderNo, String ngTraceId) {
        this.traceId = traceId;
        this.orderNo = orderNo;
        this.ngTraceId = ngTraceId;
    }

    /**
     * 基于当前MDC创建item
     */
    public static TraceItem createByCurrentMDC() {
        return new TraceItem(MDC.get(TRACE_KEY), MDC.get(ORDER_NO), MDC.get(NG_TRACE_ID));
    }

    /**
     * 往当前线程的MDC中放值
     */
    public void putAll() {
        MDC.put(TRACE_KEY, getTraceId());
        MDC.put(ORDER_NO, getOrderNo());
        MDC.put(NG_TRACE_ID, getNgTraceId());
    }

    /**
     * 移除MDC中的值
     */
    public void removeAll() {
        MDC.remove(TRACE_KEY);
        MDC.remove(ORDER_NO);
        MDC.remove(NG_TRACE_ID);
    }
}
