package com.jpeony.boot.core.chain.demo.model;

import lombok.Data;

/**
 * 过滤需要的资源
 *
 * @author yihonglei
 */
@Data
public class DemoAccessory<T> {
    /**
     * 请求上下文
     */
    private DemoContext demoContext;
    /**
     * 请求返回结果
     */
    private Object result;
}
