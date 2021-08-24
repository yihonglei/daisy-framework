package com.jpeony.order.server.service;

import com.jpeony.order.api.response.OrderInfoRes;

/**
 * @author yihonglei
 */
public interface OrderInfoService {
    OrderInfoRes getOrderInfo(Integer orderId);
}
