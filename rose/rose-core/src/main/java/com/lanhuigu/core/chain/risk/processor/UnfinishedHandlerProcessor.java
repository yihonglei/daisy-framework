package com.lanhuigu.core.chain.risk.processor;

import com.lanhuigu.core.chain.risk.RiskProcessorChain;
import com.lanhuigu.core.chain.risk.model.RiskAccessory;
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
    public void doProcessor(RiskAccessory accessory, RiskProcessorChain chain) {
        logger.info("UnfinishedHandlerProcessor.doProcessor");
        // TODO 业务处理
        chain.doProcess(accessory);
    }
}
