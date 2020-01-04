package com.lanhuigu.rocketmq.starter.annotation;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * rocketmq消费者
 * 类上面只要有该注解表示自动启动
 *
 * @author yihonglei
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RocketMqConsumer {

    /**
     * namesrvAddr地址
     * 从spring环境变量中读取
     */
    String namesrvAddr();

    /**
     * topic名字
     * 从spring环境变量中读取，为空会读取com.lanhuigu.rocketmq.starter.consumer.AbstractRocketMqConsumer#topic
     */
    String topic() default "";

    /**
     * 消费组名称
     * 从spring环境变量中读取，不能为空
     */
    String groupName();

    /**
     * instanceName
     * 从spring中读取配置，为空时用当前时间戳作为instanceName
     */
    String instanceName() default "";

    /**
     * batchMaxSize
     * 批量消费最大值
     */
    int batchMaxSize() default 16;

    /**
     * tag
     * 从spring读取配置，tag过滤 默认不过滤
     */
    String tag() default "";

    /**
     * 消费顺序，默认无序
     */
    Class<? extends MessageListener> listener() default MessageListenerConcurrently.class;
}
