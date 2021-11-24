package com.jpeony.order.server.service;

import com.jpeony.order.api.req.OrderInfoReq;
import com.jpeony.order.api.res.OrderInfoRes;

/**
 * @author yihonglei
 */
public interface OrderInfoService {
    OrderInfoRes getOrderInfo(OrderInfoReq orderInfoReq);
}
