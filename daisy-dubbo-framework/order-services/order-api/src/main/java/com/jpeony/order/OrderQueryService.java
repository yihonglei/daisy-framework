package com.jpeony.order;

import com.jpeony.order.dto.*;
import com.jpeony.order.dto.*;

public interface OrderQueryService {

    /**
     * 查询指定用户下订单总数
     *
     * @param request
     * @return
     */
    OrderCountResponse orderCount(OrderCountRequest request);


    /**
     * 查询当前用户的历史订单列表
     *
     * @param request
     * @return
     */
    OrderListResponse orderList(OrderListRequest request);


    /**
     * 查询订单明细
     *
     * @param request
     * @return
     */
    OrderDetailResponse orderDetail(OrderDetailRequest request);

    /**
     * 查询订单条目
     *
     * @param request
     * @return
     */
    OrderItemResponse orderItem(OrderItemRequest request);

}
