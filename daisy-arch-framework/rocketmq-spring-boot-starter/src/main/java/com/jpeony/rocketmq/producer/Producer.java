package com.jpeony.rocketmq.producer;

import java.util.Map;

/**
 * 生产者接口
 *
 * @author yihonglei
 */
public interface Producer {

    String UTF_8 = "UTF-8";

    /**
     * 发送消息
     *
     * @param topic 主题
     * @param tags  tag
     * @param keys  关键字
     * @param body  发送实体
     * @param ext   扩展信息
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendMessage(String topic, String tags, String keys, String body, Map<String, Object> ext) throws Exception;

    /**
     * 发送消息
     *
     * @param topic 主题
     * @param tags  tag
     * @param keys  关键字
     * @param body  发送实体
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendMessage(String topic, String tags, String keys, String body) throws Exception;

    /**
     * 发送消息
     *
     * @param topic 主题
     * @param tags  tag
     * @param body  发送实体
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendMessage(String topic, String tags, String body) throws Exception;

    /**
     * 发送消息
     *
     * @param topic 主题
     * @param body  发送实体
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendMessage(String topic, String body) throws Exception;

    /**
     * 发送顺序消息
     *
     * @param topic 主题
     * @param tags  tag
     * @param keys  关键字
     * @param body  实体数据
     * @param ext   扩展信息
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendOrderMessage(String topic, String tags, String keys, String body, Map<String, Object> ext) throws Exception;

    /**
     * 发送顺序消息
     *
     * @param topic 主题
     * @param tags  tag
     * @param keys  关键字
     * @param body  实体数据
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendOrderMessage(String topic, String tags, String keys, String body) throws Exception;

    /**
     * 发送顺序消息,默认使用fastJson序列化
     *
     * @param topic 主题
     * @param tags  tag
     * @param body  实体数据
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendOrderMessage(String topic, String tags, String body) throws Exception;

    /**
     * 发送顺序消息,默认使用fastJson序列化
     *
     * @param topic 主题
     * @param body  实体数据
     * @throws Exception 发送过程中的异常信息
     */
    boolean sendOrderMessage(String topic, String body) throws Exception;
}
