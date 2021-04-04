package com.jpeony.order.biz.handler;

import com.jpeony.commons.tool.exception.BizException;
import com.jpeony.order.biz.context.CreateOrderContext;
import com.jpeony.order.biz.context.TransHandlerContext;
import com.jpeony.order.constant.OrderRetCode;
import com.jpeony.shopping.ICartService;
import com.jpeony.shopping.dto.ClearCartItemRequest;
import com.jpeony.shopping.dto.ClearCartItemResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClearCartItemHandler extends AbstractTransHandler {
    @Reference(check = false, mock = "com.jpeony.order.biz.mock.MockCartService")
    ICartService cartService;

    //是否采用异步方式执行
    @Override
    public boolean isAsync() {
        return false;
    }

    @Override
    public boolean handle(TransHandlerContext context) {
        log.info("begin - ClearCartItemHandler-context:" + context);
        //TODO 缓存失效和下单是属于两个事物操作，需要保证成功，后续可以改造成消息队列的形式来实现
        ClearCartItemRequest request = new ClearCartItemRequest();
        CreateOrderContext createOrderContext = (CreateOrderContext) context;
        request.setProductIds(createOrderContext.getBuyProductIds());
        request.setUserId(createOrderContext.getUserId());
        ClearCartItemResponse response = cartService.clearCartItemByUserID(request);
        if (OrderRetCode.SUCCESS.getCode().equals(response.getCode())) {
            return true;
        } else {
            throw new BizException(response.getCode(), response.getMsg());
        }
    }
}
