package com.jpeony.order.biz.factory;

import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.order.biz.handler.DefaultTransPipeline;
import com.jpeony.order.biz.handler.TransPipeline;
import com.jpeony.order.biz.context.AbsTransHandlerContext;
import com.jpeony.order.biz.context.TransHandlerContext;
import com.jpeony.order.biz.TransOutboundInvoker;
import com.jpeony.order.biz.convert.TransConvert;

public abstract class AbstranctTransPipelineFactory<T extends AbstractRequest> implements TransPipelineFactory<T> {

    @Override
    public final TransOutboundInvoker build(T obj) {
        //创建转换器
        TransConvert convert = createConvert();
        //创建上下文
        TransHandlerContext context = createContext();
        //上朔
        AbsTransHandlerContext absCtx = (AbsTransHandlerContext) context;

        //set转换器
        absCtx.setConvert(convert);
        //上下文转换
        convert.convertRequest2Ctx(obj, context);
        //创建管道
        TransPipeline pipeline = createPipeline(context);
        //build管道
        doBuild(pipeline);
        //返回
        return pipeline;
    }

    protected abstract TransHandlerContext createContext();

    protected abstract void doBuild(TransPipeline pipeline);

    protected TransPipeline createPipeline(TransHandlerContext context) {
        return new DefaultTransPipeline(context);
    }

    protected abstract TransConvert createConvert();
}
