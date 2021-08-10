package com.jpeony.order.controller;

import com.jpeony.feign.order.api.OrderInfoService;
import com.jpeony.feign.order.api.pojo.dto.OrderInfoDTO;
import com.jpeony.feign.order.api.pojo.vo.OrderInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping("/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/getOrderInfo")
    public OrderInfoVO getOrderInfo(OrderInfoDTO orderInfoDTO) {
        return orderInfoService.getOrderInfo(orderInfoDTO);
    }
}
