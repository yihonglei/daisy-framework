package com.jpeony.core.chain.risk;


import com.jpeony.core.chain.risk.model.RiskAccessory;

/**
 * 风控过滤器
 *
 * @author yihonglei
 */
public interface RiskProcessor {
    /**
     * 执行过滤
     *
     * @author yihonglei
     */
    void doProcessor(RiskAccessory accessory, RiskProcessorChain chain);
}
