package com.jpeony.channel.web.controller;

import com.jpeony.channel.web.pojo.request.OrderDetailParam;
import com.jpeony.channel.web.service.OrderDetailService;
import com.jpeony.commons.core.model.ResponseDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping(value = "/channel")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PostMapping(value = "/order/getOrderDetail", produces = "application/json; charset=utf-8")
    public ResponseDataModel getOrderDetail(@RequestBody OrderDetailParam orderDetailParam) {
        return ResponseDataModel.success(orderDetailService.getOrderDetail(orderDetailParam));
    }
}
