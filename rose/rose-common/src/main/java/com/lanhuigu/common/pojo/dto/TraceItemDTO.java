package com.lanhuigu.common.pojo.dto;

import lombok.Data;
import org.slf4j.MDC;

import java.io.Serializable;

import static com.lanhuigu.common.constant.TraceConstant.NG_TRACE_ID;
import static com.lanhuigu.common.constant.TraceConstant.TRACE_KEY;

/**
 * MDC中存放的记录，便于查询日志
 *
 * @author yihonglei
 * @date 2019/11/22 11:45 AM
 */
@Data
public class TraceItemDTO implements Serializable {
    private static final long serialVersionUID = 3697722045461880439L;

    /**
     * 请求traceId
     */
    private String traceId;
    /**
     * ngTraceId
     */
    private String ngTraceId;

    public TraceItemDTO(String traceId, String ngTraceId) {
        this.traceId = traceId;
        this.ngTraceId = ngTraceId;
    }

    /**
     * 基于当前MDC创建item
     */
    public static TraceItemDTO createByCurrentMDC() {
        return new TraceItemDTO(MDC.get(TRACE_KEY), MDC.get(NG_TRACE_ID));
    }

    /**
     * 往当前线程的MDC中放值
     */
    public void putAll() {
        MDC.put(TRACE_KEY, getTraceId());
        MDC.put(NG_TRACE_ID, getNgTraceId());
    }

    /**
     * 移除MDC中的值
     */
    public void removeAll() {
        MDC.remove(TRACE_KEY);
        MDC.remove(NG_TRACE_ID);
    }
}
