package com.jpeony.order.server.service.impl;

import com.jpeony.order.api.request.OrderInfoReq;
import com.jpeony.order.api.response.OrderInfoRes;
import com.jpeony.order.server.mapper.OrderInfoMapper;
import com.jpeony.order.server.pojo.domain.OrderInfoDO;
import com.jpeony.order.server.service.OrderInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Override
    public OrderInfoRes getOrderInfo(OrderInfoReq orderInfoReq) {
        OrderInfoRes orderInfoRes = new OrderInfoRes();
        OrderInfoDO orderInfoDO = orderInfoMapper.queryOrderInfo(orderInfoReq.getOrderId());
        if (orderInfoDO == null) {
            orderInfoDO = orderInfoMapper.queryOrderInfoMaster(orderInfoReq.getOrderId());
        }
        orderInfoRes.setOrderId(orderInfoDO.getOrderId());
        orderInfoRes.setOrderName(orderInfoDO.getOrderName());
        orderInfoRes.setStatus(orderInfoDO.getStatus());

        return orderInfoRes;
    }
}
