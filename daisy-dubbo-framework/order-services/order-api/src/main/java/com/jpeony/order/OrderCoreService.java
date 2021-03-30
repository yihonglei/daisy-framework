package com.jpeony.order;

import com.jpeony.order.dto.*;
import com.jpeony.order.dto.*;

/**
 * 订单相关业务
 */
public interface OrderCoreService {

    /**
     * 创建订单
     *
     * @param request
     * @return
     */
    CreateOrderResponse createOrder(CreateOrderRequest request);

    /**
     * 取消订单
     *
     * @param request
     * @return
     */
    CancelOrderResponse cancelOrder(CancelOrderRequest request);


    /**
     * 删除订单
     *
     * @param request
     * @return
     */
    DeleteOrderResponse deleteOrder(DeleteOrderRequest request);


    void updateOrder(Integer status, String orderId);

    /**
     * 删除订单与交易
     *
     * @param request
     */
    void deleteOrderWithTransaction(DeleteOrderRequest request);


}
