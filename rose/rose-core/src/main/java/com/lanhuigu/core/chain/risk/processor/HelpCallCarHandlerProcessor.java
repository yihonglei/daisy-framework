package com.lanhuigu.core.chain.risk.processor;

import com.lanhuigu.core.chain.risk.RiskProcessorChain;
import com.lanhuigu.core.chain.risk.model.RiskAccessory;
import org.springframework.stereotype.Service;

/**
 * 代叫车风控
 *
 * @author yihonglei
 */
@Service
public class HelpCallCarHandlerProcessor extends AbstractRiskProcessor {
    @Override
    public void doProcessor(RiskAccessory accessory, RiskProcessorChain chain) {
        logger.info("HelpCallCarHandlerProcessor.doProcessor");
        // TODO 业务处理
        chain.doProcess(accessory);
    }
}
