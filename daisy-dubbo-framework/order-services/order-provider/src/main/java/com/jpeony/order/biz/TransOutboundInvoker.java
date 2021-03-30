package com.jpeony.order.biz;

import com.jpeony.order.biz.context.TransHandlerContext;

public interface TransOutboundInvoker {

    /**
     * 启动流程
     */
    void start();

    /**
     * 终止流程
     */
    void shutdown();

    /**
     * 用于获取返回值
     */
    <T extends TransHandlerContext> T getContext();
}
