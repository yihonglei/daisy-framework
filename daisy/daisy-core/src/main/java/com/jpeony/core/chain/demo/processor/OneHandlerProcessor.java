package com.jpeony.core.chain.demo.processor;

import com.jpeony.core.chain.demo.DemoProcessorChain;
import com.jpeony.core.chain.demo.model.DemoAccessory;
import com.jpeony.core.pojo.vo.RiskVO;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Service
public class OneHandlerProcessor extends AbstractDemoProcessor {
    @Override
    public void doProcessor(DemoAccessory accessory, DemoProcessorChain chain) {
        logger.info("HelpCallCarHandlerProcessor.doProcessor");
        // TODO 业务处理
        RiskVO result = (RiskVO) accessory.getResult();
        result.setRiskFlag(1);
        result.setUserType(1);

        chain.doProcess(accessory);
    }
}
