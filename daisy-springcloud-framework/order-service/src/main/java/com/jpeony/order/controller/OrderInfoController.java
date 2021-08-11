package com.jpeony.order.controller;

import com.jpeony.feign.order.api.OrderInfoService;
import com.jpeony.feign.order.api.pojo.dto.OrderInfoDTO;
import com.jpeony.feign.order.api.pojo.vo.OrderInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yihonglei
 */
@RestController
@RequestMapping(value = "/order", produces = "application/json; charset=utf-8")
public class OrderInfoController {
    @Autowired
    private OrderInfoService orderInfoService;

    @PostMapping("/getOrderInfo")
    public OrderInfoVO getOrderInfo(@RequestBody OrderInfoDTO orderInfoDTO) {
        return orderInfoService.getOrderInfo(orderInfoDTO);
    }
}
