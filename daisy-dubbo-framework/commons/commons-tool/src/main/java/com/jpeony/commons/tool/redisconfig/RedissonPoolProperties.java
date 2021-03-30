package com.jpeony.commons.tool.redisconfig;

import lombok.Data;

@Data
public class RedissonPoolProperties {

    private int maxIdle;
    /**
     * 连接池中的最大空闲连接
     **/

    private int minIdle;
    /**
     * 最小连接数
     **/

    private int maxActive;
    /**
     * 连接池最大连接数
     **/

    private int maxWait;/**连接池最大阻塞等待时间**/

}
