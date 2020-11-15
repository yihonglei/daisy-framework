
package com.jpeony.core.chain.demo.processor;

import com.jpeony.core.chain.demo.DemoProcessorChain;
import com.jpeony.core.chain.demo.model.DemoAccessory;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Service
public class ThreeHandlerProcessor extends AbstractDemoProcessor {
    @Override
    public void doProcessor(DemoAccessory accessory, DemoProcessorChain chain) {
        logger.info("UserTypeHandlerProcessor.doProcessor");
        // TODO 业务处理
        chain.doProcess(accessory);
    }
}
