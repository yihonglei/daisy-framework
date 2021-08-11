package com.jpeony.channel.web.service.impl;

import com.jpeony.channel.web.pojo.dto.OrderDetailDTO;
import com.jpeony.channel.web.pojo.vo.OrderDetailVO;
import com.jpeony.channel.web.service.OrderDetailService;
import com.jpeony.feign.order.api.OrderInfoService;
import com.jpeony.feign.order.api.pojo.dto.OrderInfoDTO;
import com.jpeony.feign.order.api.pojo.vo.OrderInfoVO;
import com.jpeony.feign.user.api.UserInfoService;
import com.jpeony.feign.user.api.pojo.dto.UserDTO;
import com.jpeony.feign.user.api.pojo.vo.UserVO;
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
    private UserInfoService userInfoService;
    @Autowired
    private OrderInfoService orderInfoService;

    @Override
    public OrderDetailVO getOrderDetail(OrderDetailDTO orderDetailDTO) {
        /* 用户信息 */
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName("user-001");
        userDTO.setAge(18);
        UserVO userInfo = userInfoService.getUserInfo(userDTO);

        /* 订单信息 */
        OrderInfoDTO orderInfoDTO = new OrderInfoDTO();
        orderInfoDTO.setOrderName("order-001");
        orderInfoDTO.setStatus(45);
        OrderInfoVO orderInfo = orderInfoService.getOrderInfo(orderInfoDTO);

        /* 订单详情 */
        OrderDetailVO orderDetailVO = new OrderDetailVO();
        orderDetailVO.setUserName(userInfo.getUserName());
        orderDetailVO.setAge(userDTO.getAge());
        orderDetailVO.setOrderName(orderInfo.getOrderName());
        orderDetailVO.setStatus(orderInfo.getStatus());
        orderDetailVO.setOrderId(orderInfo.getOrderId());

        return orderDetailVO;
    }
}
