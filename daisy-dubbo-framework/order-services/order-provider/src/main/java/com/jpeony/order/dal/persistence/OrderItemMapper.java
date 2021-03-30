package com.jpeony.order.dal.persistence;

import com.jpeony.commons.tool.tkmapper.TkMapper;
import com.jpeony.order.dal.entitys.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderItemMapper extends TkMapper<OrderItem> {
    List<OrderItem> queryByOrderId(@Param("orderId") String orderId);
    void updateStockStatus(@Param("status") Integer status,@Param("orderId") String orderId);
}