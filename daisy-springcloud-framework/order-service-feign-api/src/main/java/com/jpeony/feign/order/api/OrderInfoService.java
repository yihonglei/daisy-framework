package com.jpeony.feign.order.api;

import com.jpeony.feign.order.api.pojo.dto.OrderInfoDTO;
import com.jpeony.feign.order.api.pojo.vo.OrderInfoVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author yihonglei
 */
@FeignClient(name = "order-service")
public interface OrderInfoService {
    @PostMapping(value = "/user/getOrderInfo")
    OrderInfoVO getOrderInfo(OrderInfoDTO orderInfoDTO);
}
