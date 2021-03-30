package com.jpeony.order.converter;

import com.jpeony.order.dal.entitys.Order;
import com.jpeony.order.dal.entitys.OrderItem;
import com.jpeony.order.dal.entitys.OrderShipping;
import com.jpeony.order.dto.*;
import com.jpeony.order.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderConverter {

    @Mappings({})
    OrderDetailResponse order2res(Order order);

    @Mappings({})
    OrderDetailInfo order2detail(Order order);

    @Mappings({})
    OrderItemDto item2dto(OrderItem item);

    @Mappings({})
    OrderShippingDto shipping2dto(OrderShipping shipping);


    List<OrderItemDto> item2dto(List<OrderItem> items);

    @Mappings({})
    OrderItemResponse item2res(OrderItem orderItem);

    @Mappings({})
    OrderDto order2dto(Order order);
}
