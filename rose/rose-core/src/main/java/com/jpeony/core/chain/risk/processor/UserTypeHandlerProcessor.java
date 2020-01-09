
package com.jpeony.core.chain.risk.processor;

import com.jpeony.core.chain.risk.RiskProcessorChain;
import com.jpeony.core.chain.risk.model.RiskAccessory;
import org.springframework.stereotype.Service;

/**
 * 用户类型风控
 *
 * @author yihonglei
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
