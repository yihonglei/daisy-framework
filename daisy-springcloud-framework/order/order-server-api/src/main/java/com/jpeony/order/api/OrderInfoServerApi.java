package com.jpeony.order.api;

import com.jpeony.order.api.request.OrderInfoReq;
import com.jpeony.order.api.response.OrderInfoRes;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author yihonglei
 */
@FeignClient(name = "order-server")
public interface OrderInfoServerApi {
    @PostMapping(value = "/order/getOrderInfo")
    OrderInfoRes getOrderInfo(@RequestBody OrderInfoReq orderInfoReq);
}
