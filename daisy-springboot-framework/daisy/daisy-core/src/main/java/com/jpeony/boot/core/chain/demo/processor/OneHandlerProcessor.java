package com.jpeony.boot.core.chain.demo.processor;

import com.jpeony.boot.core.chain.demo.DemoProcessorChain;
import com.jpeony.boot.core.pojo.vo.RiskVO;
import com.jpeony.boot.core.chain.demo.model.DemoAccessory;
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
