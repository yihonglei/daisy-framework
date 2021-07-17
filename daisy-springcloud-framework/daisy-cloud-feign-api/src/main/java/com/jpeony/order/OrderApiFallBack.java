package com.jpeony.order;

import org.springframework.stereotype.Component;

/**
 * Order订单，服务降级接口
 *
 * @author yihonglei
 */
@Component
public class OrderApiFallBack implements OrderApi {
    @Override
    public String queryOrdersByUserId() {
        return "触发服务降级接口";
    }
}