package com.jpeony.boot.test.mq;

import com.jpeony.boot.core.mq.TestRocketMqProducer;
import com.jpeony.boot.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yihonglei
 */
@Slf4j
public class MQTest extends BaseServletTest {

    @Autowired
    private TestRocketMqProducer producer;

    @Test
    public void testProducer() throws Exception {
        boolean result = producer.sendMessage("testTopic", "*", "This my rocketmq message!");
        log.info("send message result = {}", result);
    }
}
