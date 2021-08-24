package com.jpeony.channel.web.service.impl;

import com.jpeony.channel.web.pojo.request.OrderDetailParam;
import com.jpeony.channel.web.pojo.response.OrderDetailVO;
import com.jpeony.channel.web.service.OrderDetailService;
import com.jpeony.order.api.OrderInfoServerApi;
import com.jpeony.order.api.request.OrderInfoReq;
import com.jpeony.order.api.response.OrderInfoRes;
import com.jpeony.user.api.UserInfoServerApi;
import com.jpeony.user.api.request.UserInfoReq;
import com.jpeony.user.api.response.UserInfoRes;
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
    private UserInfoServerApi userInfoServerApi;
    @Autowired
    private OrderInfoServerApi orderInfoServerApi;

    @Override
    public OrderDetailVO getOrderDetail(OrderDetailParam orderDetailParam) {
        /* 用户信息 */
        UserInfoReq userInfoReq = new UserInfoReq();
        userInfoReq.setUserId(1);
        UserInfoRes userInfoRes = userInfoServerApi.getUserInfo(userInfoReq);

        /* 订单信息 */
        OrderInfoReq orderInfoReq = new OrderInfoReq();
        orderInfoReq.setOrderId(orderDetailParam.getOrderId());
        OrderInfoRes orderInfoRes = orderInfoServerApi.getOrderInfo(orderInfoReq);

        /* 订单详情 */
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setUserName(userInfoRes.getUserName());
        orderDetailVO.setAge(userInfoRes.getAge());
        orderDetailVO.setOrderName(orderInfoRes.getOrderName());
        orderDetailVO.setStatus(orderInfoRes.getStatus());
        orderDetailVO.setOrderId(orderInfoRes.getOrderId());

        return orderDetailVO;
    }
}
