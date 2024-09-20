package com.jpeony.commons.core.logback;

import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

import static com.jpeony.commons.core.constant.TraceConstant.*;


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

    public LogTraceInfo(String traceId, String orderNo) {
        this.traceId = traceId;
        this.orderNo = orderNo;
    }

    /**
     * 基于当前MDC创建item
     */
    public static LogTraceInfo createByCurrentMDC() {
        return new LogTraceInfo(MDC.get(TRACE_KEY), MDC.get(ORDER_NO));
    }

    /**
     * 往当前线程的MDC中放值
     */
    public void putAll() {
        MDC.put(TRACE_KEY, getTraceId());
        MDC.put(ORDER_NO, getOrderNo());
    }

    /**
     * 移除MDC中的值
     */
    public void removeAll() {
        MDC.remove(TRACE_KEY);
        MDC.remove(ORDER_NO);
    }
}
