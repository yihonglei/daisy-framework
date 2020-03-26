package com.jpeony.core.chain.risk.processor;

import com.jpeony.core.chain.risk.RiskProcessorChain;
import com.jpeony.core.chain.risk.model.RiskAccessory;
import org.springframework.stereotype.Service;

/**
 * 未完成订单风控
 *
 * @author yihonglei
 */
@Service
public class UnfinishedHandlerProcessor extends AbstractRiskProcessor {
    @Override
    public void doProcessor(RiskAccessory accessory, RiskProcessorChain chain) {
        logger.info("UnfinishedHandlerProcessor.doProcessor");
        // TODO 业务处理
        chain.doProcess(accessory);
    }
}
