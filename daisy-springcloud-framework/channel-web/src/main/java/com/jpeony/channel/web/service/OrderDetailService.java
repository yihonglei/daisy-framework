package com.jpeony.channel.web.service;

import com.jpeony.channel.web.pojo.dto.OrderDetailDTO;
import com.jpeony.channel.web.pojo.vo.OrderDetailVO;

/**
 * @author yihonglei
 */
public interface OrderDetailService {
    OrderDetailVO getOrderDetail(OrderDetailDTO orderDetailDTO);
}
