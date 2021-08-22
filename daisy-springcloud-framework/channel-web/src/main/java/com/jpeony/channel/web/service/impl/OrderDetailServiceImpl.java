package com.jpeony.channel.web.service.impl;

import com.jpeony.channel.web.pojo.request.OrderDetailParam;
import com.jpeony.channel.web.pojo.response.OrderDetailVO;
import com.jpeony.channel.web.service.OrderDetailService;
import com.jpeony.order.api.OrderServerApi;
import com.jpeony.order.api.request.OrderInfoParam;
import com.jpeony.order.api.response.OrderInfoDTO;
import com.jpeony.user.api.UserServerApi;
import com.jpeony.user.api.request.UserInfoParam;
import com.jpeony.user.api.response.UserInfoDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    private UserServerApi userInfoService;
    @Autowired
    private OrderServerApi orderServerApi;

    @Override
    public OrderDetailVO getOrderDetail(OrderDetailParam orderDetailParam) {
        /* 用户信息 */
        UserInfoParam userInfoParam = new UserInfoParam();
        userInfoParam.setUserId(1);
        UserInfoDTO userInfoDTO = userInfoService.getUserInfo(userInfoParam);

        /* 订单信息 */
        OrderInfoParam orderInfoParam = new OrderInfoParam();
        orderInfoParam.setOrderId(orderDetailParam.getOrderId());
        OrderInfoDTO orderInfoDTO = orderServerApi.getOrderInfo(orderInfoParam);

        /* 订单详情 */
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setUserName(userInfoDTO.getUserName());
        orderDetailVO.setAge(userInfoDTO.getAge());
        orderDetailVO.setOrderName(orderInfoDTO.getOrderName());
        orderDetailVO.setStatus(orderInfoDTO.getStatus());
        orderDetailVO.setOrderId(orderInfoDTO.getOrderId());

        return orderDetailVO;
    }
}
