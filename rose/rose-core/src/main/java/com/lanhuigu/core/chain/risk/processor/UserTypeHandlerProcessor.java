
package com.lanhuigu.core.chain.risk.processor;

import com.lanhuigu.core.chain.risk.RiskProcessorChain;
import com.lanhuigu.core.chain.risk.model.RiskAccessory;
import org.springframework.stereotype.Service;

/**
 * 用户类型风控
 *
 * @author yihonglei
 * @date 2019/10/31 5:45 PM
 */
@Service
public class UserTypeHandlerProcessor extends AbstractRiskProcessor {
    @Override
    public void doProcessor(RiskAccessory accessory, RiskProcessorChain chain) {
        logger.info("UserTypeHandlerProcessor.doProcessor");
        // 业务处理
        String orderNo = accessory.getRiskContext().getOrderNo();
        if (orderNo.startsWith("P")) {
            accessory.getRiskVO().setRiskFlag(1);
        }
        chain.doProcess(accessory);
    }
}
