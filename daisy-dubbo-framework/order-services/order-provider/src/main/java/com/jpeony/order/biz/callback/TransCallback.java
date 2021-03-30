package com.jpeony.order.biz.callback;

import com.jpeony.order.biz.context.TransHandlerContext;

/**
 * 交易回调
 */
public interface TransCallback {

    void onDone(TransHandlerContext context);
}
