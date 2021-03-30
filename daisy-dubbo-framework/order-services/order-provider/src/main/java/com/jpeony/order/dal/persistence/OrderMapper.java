package com.jpeony.order.dal.persistence;

import com.jpeony.commons.tool.tkmapper.TkMapper;
import com.jpeony.order.dal.entitys.Order;

public interface OrderMapper extends TkMapper<Order> {
    Long countAll();
}