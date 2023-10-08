package com.jpeony.boot.core.chain.demo.processor;

import com.jpeony.boot.core.chain.demo.DemoProcessorChain;
import com.jpeony.boot.core.chain.demo.model.DemoAccessory;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Service
public class TwoHandlerProcessor extends AbstractDemoProcessor {
    @Override
    public void doProcessor(DemoAccessory accessory, DemoProcessorChain chain) {
        logger.info("UnfinishedHandlerProcessor.doProcessor");
        // TODO 业务处理
        chain.doProcess(accessory);
    }
}
