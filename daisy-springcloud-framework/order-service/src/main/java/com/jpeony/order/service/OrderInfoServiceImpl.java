package com.jpeony.order.service;

import com.jpeony.feign.order.api.OrderInfoService;
import com.jpeony.feign.order.api.request.OrderInfoParam;
import com.jpeony.feign.order.api.response.OrderInfoDTO;
import com.jpeony.order.mapper.OrderInfoMapper;
import com.jpeony.order.pojo.dto.OrderInfoDetailDTO;
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
    public OrderInfoDTO getOrderInfo(OrderInfoParam orderInfoParam) {
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();

        OrderInfoDetailDTO orderInfoDetailDTO = orderInfoMapper.queryOrderInfoDetail(orderInfoParam.getOrderName());
        orderInfoDTO.setOrderId(orderInfoDetailDTO.getOrderId());
        orderInfoDTO.setOrderName(orderInfoDetailDTO.getOrderName());
        orderInfoDTO.setStatus(orderInfoDetailDTO.getStatus());

        return orderInfoDTO;
    }
}
