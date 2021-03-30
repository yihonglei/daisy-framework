package com.jpeony.order.biz.handler;

import com.jpeony.order.biz.callback.TransCallback;
import com.jpeony.order.biz.context.TransHandlerContext;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class TransHandlerNode {

    private TransHandler handler;

    private TransHandlerNode next = null;

    public void exec(TransHandlerContext context) {
        AbstractTransHandler transHandler = (AbstractTransHandler) handler;
        boolean success = handler.handle(context);
        //回调函数
        execCallbacks(transHandler.getTransCallback(), context, null);
        if (next != null) {
            if (success) {
                if (transHandler.isAsync()) {
                    //TODO 如果为true，则采用异步线程去执行任务

                }
                next.exec(context);
            }
        }
    }

    private void execCallbacks(TransCallback callback, TransHandlerContext context, Throwable ex) {
        try {
            if (ex == null&&callback!=null) {
                callback.onDone(context);
            }
        } catch (Exception e) {
            log.warn("Pipeline回调处理异常.", e);
            //TODO 异常处理
        }
    }
}
