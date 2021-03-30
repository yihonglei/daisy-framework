package com.jpeony.order.biz.factory;

import com.jpeony.order.biz.TransOutboundInvoker;

public interface TransPipelineFactory<T> {

    TransOutboundInvoker build(T obj);
}
