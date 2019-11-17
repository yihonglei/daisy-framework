package com.lanhuigu.core.chain.risk.processor;

import com.lanhuigu.core.chain.risk.RiskProcessorChain;
import com.lanhuigu.core.chain.risk.model.Accessory;
import com.lanhuigu.core.chain.risk.model.RiskResult;
import org.springframework.stereotype.Service;

/**
 * 未完成订单风控
 *
 * @author yihonglei
 * @date 2019/10/31 5:54 PM
 */
@Service
public class UnfinishedHandlerProcessor extends AbstractRiskProcessor {
    @Override
    public void doProcessor(Accessory accessory, RiskResult result, RiskProcessorChain chain) {
        logger.info("UnfinishedHandlerProcessor.doProcessor");
        chain.doProcess(accessory, result);
    }
}
