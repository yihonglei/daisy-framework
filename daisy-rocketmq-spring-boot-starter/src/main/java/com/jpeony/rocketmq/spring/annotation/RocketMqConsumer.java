package com.jpeony.rocketmq.spring.annotation;

import org.apache.rocketmq.client.consumer.listener.MessageListener;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 消费者注解
 *
 * @author yihonglei
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Component
public @interface RocketMqConsumer {

    /**
     * namesrvAddr
     * 从spring环境变量中读取 ${namesrvAddr:} :后面为默认值
     */
    String namesrvAddr();

    /**
     * topic
     * 从spring环境变量中读取 ${topic:} :后面为默认值
     */
    String topic();

    /**
     * 消费组名称
     * 从spring环境变量中读取 ${groupName:} :后面为默认值，不能为空
     */
    String groupName();

    /**
     * instanceName
     * 从spring中读取配置,为空时用当前时间戳作为instanceName ${instanceName:} :后面为默认值
     */
    String instanceName() default "";

    /**
     * batchMaxSize
     * 批量消费最大值
     */
    int batchMaxSize() default 16;

    /**
     * tag
     * 从spring读取配置,tag过滤 默认不过滤 ${tag:*}
     */
    String tag() default "*";

    /**
     * 是否开启记录跟踪功能 true表示开启
     */
    boolean enableMsgTrace() default true;

    /**
     * 追踪日志topic 默认 RMQ_SYS_TRACE_TOPIC,${customizedTraceTopic:} :后面为默认值
     */
    String customizedTraceTopic() default "";

    /**
     * 是否忽略日志 true表示忽略 false表示不忽略
     */
    boolean ignoreLog() default false;

    /**
     * 默认从末尾消费
     */
    ConsumeFromWhere from() default ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET;

    /**
     * 消费顺序 默认无序
     */
    Class<? extends MessageListener> listener() default MessageListenerConcurrently.class;

}
