package com.jpeony.core.chain.demo;


import com.jpeony.core.chain.demo.model.DemoAccessory;

/**
 * 处理器
 *
 * @author yihonglei
 */
public interface DemoProcessor {
    /**
     * 执行过滤
     */
    void doProcessor(DemoAccessory accessory, DemoProcessorChain chain);
}
