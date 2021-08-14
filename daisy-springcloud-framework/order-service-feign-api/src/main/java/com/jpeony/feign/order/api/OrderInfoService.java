package com.jpeony.feign.order.api;

import com.jpeony.feign.order.api.request.OrderInfoParam;
import com.jpeony.feign.order.api.response.OrderInfoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yihonglei
 */
@FeignClient(name = "order-service")
public interface OrderInfoService {
    @PostMapping(value = "/order/getOrderInfo")
    OrderInfoDTO getOrderInfo(@RequestBody OrderInfoParam orderInfoParam);
}
