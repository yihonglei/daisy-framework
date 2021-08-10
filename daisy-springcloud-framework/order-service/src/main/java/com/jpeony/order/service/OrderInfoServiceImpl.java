package com.jpeony.order.service;

import com.jpeony.feign.order.api.OrderInfoService;
import com.jpeony.feign.order.api.pojo.dto.OrderInfoDTO;
import com.jpeony.feign.order.api.pojo.vo.OrderInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service
public class OrderInfoServiceImpl implements OrderInfoService {

    @Override
    public OrderInfoVO getOrderInfo(OrderInfoDTO orderInfoDTO) {
        OrderInfoVO orderInfoVO = new OrderInfoVO();
        orderInfoVO.setOrderId(orderInfoDTO.getOrderId());
        orderInfoVO.setStatus(orderInfoDTO.getStatus());
        orderInfoVO.setName("orderName-001");
        return orderInfoVO;
    }
}
