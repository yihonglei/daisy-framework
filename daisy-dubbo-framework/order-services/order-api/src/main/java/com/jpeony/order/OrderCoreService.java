package com.jpeony.order;

import com.jpeony.order.dto.*;
import com.jpeony.order.dto.*;

/**
 * 订单相关业务
 */
public interface OrderCoreService {

    /**
     * 创建订单
     */
    CreateOrderResponse createOrder(CreateOrderRequest request);

    /**
     * 取消订单
     */
    CancelOrderResponse cancelOrder(CancelOrderRequest request);


    /**
     * 删除订单
     */
    DeleteOrderResponse deleteOrder(DeleteOrderRequest request);


    void updateOrder(Integer status, String orderId);

    /**
     * 删除订单与交易
     */
    void deleteOrderWithTransaction(DeleteOrderRequest request);


}
