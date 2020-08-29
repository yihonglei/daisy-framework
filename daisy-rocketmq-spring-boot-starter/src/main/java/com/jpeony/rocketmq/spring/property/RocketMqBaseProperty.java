package com.jpeony.rocketmq.spring.property;

import lombok.Data;

/**
 * 基础配置
 *
 * @author yihonglei
 */
@Data
public class RocketMqBaseProperty {
    private String namesrvAddr;
    private String groupName;
    private String topic;
    private String tag;
    private int batchMaxSize;
    private String instanceName;
}