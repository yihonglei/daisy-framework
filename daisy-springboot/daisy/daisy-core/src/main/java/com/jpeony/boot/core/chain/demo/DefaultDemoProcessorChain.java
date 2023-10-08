package com.jpeony.boot.core.chain.demo;

import com.google.common.collect.Lists;
import com.jpeony.boot.core.chain.demo.model.DemoAccessory;

import java.util.List;

/**
 * 负责链条的添加和执行，链条内容为各个处理器
 *
 * @author yihonglei
 */
public class DefaultDemoProcessorChain<E> implements DemoProcessorChain<E> {
    private List<DemoProcessor> filters = Lists.newArrayList();
    private int index = 0;

    @Override
    public E doProcess(DemoAccessory accessory) {
        if (index == filters.size()) {
            return (E) accessory.getResult();
        }

        filters.get(index++).doProcessor(accessory, this);

        return (E) accessory.getResult();
    }

    @Override
    public void addProcessorChain(DemoProcessor processor) {
        this.filters.add(processor);
    }
}
