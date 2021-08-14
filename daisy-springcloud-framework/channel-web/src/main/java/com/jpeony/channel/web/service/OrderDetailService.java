package com.jpeony.channel.web.service;

import com.jpeony.channel.web.pojo.request.OrderDetailParam;
import com.jpeony.channel.web.pojo.response.OrderDetailVO;

/**
 * @author yihonglei
 */
public interface OrderDetailService {
    OrderDetailVO getOrderDetail(OrderDetailParam orderDetailParam);
}
