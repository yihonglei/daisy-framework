package com.jpeony.boot.core.chain.demo;

import com.jpeony.boot.core.chain.demo.model.DemoAccessory;

/**
 * 处理链
 *
 * @author yihonglei
 */
public interface DemoProcessorChain<E> {

    E doProcess(DemoAccessory accessory);

    /**
     * 添加责任链
     */
    void addProcessorChain(DemoProcessor processor);

}
