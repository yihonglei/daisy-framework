package com.jpeony.order.biz.handler;

import com.jpeony.order.biz.TransOutboundInvoker;

public interface TransPipeline extends TransOutboundInvoker {

    void addFirst(TransHandler... handlers);

    void addLast(TransHandler... handlers);
}
