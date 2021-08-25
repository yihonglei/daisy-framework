package com.jpeony.order.server.controller;

import com.jpeony.order.api.request.OrderInfoReq;
import com.jpeony.order.api.response.OrderInfoRes;
import com.jpeony.order.server.service.OrderInfoService;
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
    public OrderInfoRes getOrderInfo(@RequestBody OrderInfoReq orderInfoReq) {
        return orderInfoService.getOrderInfo(orderInfoReq);
    }
}
