package com.jpeony.redis.property;

import org.apache.commons.pool2.impl.BaseObjectPoolConfig;

/**
 * redis 配置:
 * name              集群名称、beanName
 * cluster           格式为ip:port,ip:port
 * prefix            redis-cluster全局KEY前缀
 * password          密码
 * maxIdle           最多能保留多少个空闲对象
 * maxTotal          最多能保留多少对象
 * minIdle           最少有多少个空闲对象
 * connectionTimeout 连接超时
 * soTimeout         返回值的超时时间
 * maxAttempts       出现异常最大重试次数
 * minEvictableIdleTimeMillis 连接空闲多长时间后将被释放，timeBetweenEvictionRunsMillis > 0 才有意义
 * timeBetweenEvictionRunsMillis 空闲连接每次扫描的时间间隔
 *
 * @author yihonglei
 */
public class RedisProperties {
    private String name;
    private String cluster;
    private String prefix;
    private String password;
    private int connectionTimeout = 1500;
    private int soTimeout = 2000;
    private int maxIdle = 30;
    private int maxTotal = 1024;
    private int minIdle = 0;
    private int maxAttempts = 2;
    private long minEvictableIdleTimeMillis = 1000L * 60L * 30L;
    private long timeBetweenEvictionRunsMillis = 1000L * 60L * 10;
    private String evictionPolicyClassName = BaseObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME;
    /**
     * 序列化方式的使用：
     * 1. 系统已实现可选值: jackson、jdk
     * 2. 或指定自己的实现类(全类名)，该类必须实现 Serializer 接口
     */
    private String serializer;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public int getMaxAttempts() {
        return maxAttempts;
    }

    public void setMaxAttempts(int maxAttempts) {
        this.maxAttempts = maxAttempts;
    }

    public long getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public void setMinEvictableIdleTimeMillis(long minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public long getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public void setTimeBetweenEvictionRunsMillis(long timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public String getEvictionPolicyClassName() {
        return evictionPolicyClassName;
    }

    public void setEvictionPolicyClassName(String evictionPolicyClassName) {
        this.evictionPolicyClassName = evictionPolicyClassName;
    }

    public String getSerializer() {
        return serializer;
    }

    public void setSerializer(String serializer) {
        this.serializer = serializer;
    }
}
