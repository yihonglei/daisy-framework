package com.jpeony.order;

import com.jpeony.order.dto.*;
import com.jpeony.order.dto.*;

public interface OrderQueryService {

    /**
     * 查询指定用户下订单总数
     */
    OrderCountResponse orderCount(OrderCountRequest request);
    
    /**
     * 查询当前用户的历史订单列表
     */
    OrderListResponse orderList(OrderListRequest request);

    /**
     * 查询订单明细
     */
    OrderDetailResponse orderDetail(OrderDetailRequest request);

    /**
     * 查询订单条目
     */
    OrderItemResponse orderItem(OrderItemRequest request);

}
