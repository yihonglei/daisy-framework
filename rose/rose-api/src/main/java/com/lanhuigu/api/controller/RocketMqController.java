package com.lanhuigu.api.controller;

import com.lanhuigu.common.pojo.dto.TestDTO;
import com.lanhuigu.common.util.ApiResponse;
import com.lanhuigu.core.mq.TestRocketMqProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RocketMq生产者消费者测试
 *
 * @author yihonglei
 */
@RestController
@RequestMapping("/rocketmq/test")
public class RocketMqController {
    @Autowired
    private TestRocketMqProducer producer;

    @RequestMapping("/producer")
    public ApiResponse producer() {
        boolean b = false;
        try {
            b = producer.sendMessage("testTopic", "*", "This my rocketmq message!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ApiResponse.success(b);
    }
}
