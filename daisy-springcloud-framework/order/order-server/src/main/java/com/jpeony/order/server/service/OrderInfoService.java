package com.jpeony.order.server.service;

import com.jpeony.order.api.response.OrderInfoDTO;

/**
 * @author yihonglei
 */
public interface OrderInfoService {
    OrderInfoDTO getOrderInfo(Integer orderId);
}
