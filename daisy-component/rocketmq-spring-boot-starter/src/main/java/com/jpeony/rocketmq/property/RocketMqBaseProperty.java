package com.jpeony.rocketmq.property;

import lombok.Data;

/**
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