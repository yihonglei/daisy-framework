package com.jpeony.order.server.service.impl;

import com.jpeony.order.api.response.OrderInfoDTO;
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
    public OrderInfoDTO getOrderInfo(Integer orderId) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();

        OrderInfoDO orderInfoDO = orderInfoMapper.queryOrderInfo(orderId);
        if (orderInfoDO == null) {
            orderInfoDO = orderInfoMapper.queryOrderInfoMaster(orderId);
        }
        orderInfoDTO.setOrderId(orderInfoDO.getOrderId());
        orderInfoDTO.setOrderName(orderInfoDO.getOrderName());
        orderInfoDTO.setStatus(orderInfoDO.getStatus());

        return orderInfoDTO;
    }
}
