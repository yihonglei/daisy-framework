package com.lanhuigu.rocketmq.starter.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * rocketMq 生产者注解
 *
 * @author yihonglei
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface RocketMqProducer {
    /**
     * namesrvAddr地址
     * 从spring环境变量中读取
     */
    String namesrvAddr();

    /**
     * topic名字
     * 从spring环境变量中读取为空会读取com.lanhuigu.rocketmq.starter.consumer.AbstractRocketMqConsumer#topic
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
     * tag
     * 从spring读取配置，tag过滤 默认不过滤
     */
    String tag() default "";

    /**
     * 失败尝试次数，默认3次
     */
    int retryTimesWhenSendFailed() default 3;

    /**
     * 发送超时时间 默认3000ms
     */
    int sendMsgTimeout() default 3 * 1000;

    /**
     * 是否打印发送成功的日志 默认打印
     */
    boolean ignoreLog() default false;
}
