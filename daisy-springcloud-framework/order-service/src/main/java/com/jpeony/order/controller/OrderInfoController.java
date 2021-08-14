package com.jpeony.order.controller;

import com.jpeony.feign.order.api.OrderInfoService;
import com.jpeony.feign.order.api.request.OrderInfoParam;
import com.jpeony.feign.order.api.response.OrderInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping(value = "/order")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping(value = "/getOrderInfo", produces = "application/json; charset=utf-8")
    public OrderInfoDTO getOrderInfo(@RequestBody OrderInfoParam orderInfoParam) {
        return orderInfoService.getOrderInfo(orderInfoParam);
    }
}
