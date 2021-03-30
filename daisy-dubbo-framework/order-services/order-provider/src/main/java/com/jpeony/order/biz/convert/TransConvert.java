
package com.jpeony.order.biz.convert;


import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.core.AbstractResponse;
import com.jpeony.order.biz.context.TransHandlerContext;

public interface TransConvert {
    /**
     * 请求转上下文
     */
    TransHandlerContext convertRequest2Ctx(AbstractRequest req, TransHandlerContext context);

    /**
     * 上下文转应答
     */
    AbstractResponse convertCtx2Respond(TransHandlerContext ctx);
}
