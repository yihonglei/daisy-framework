package com.lanhuigu.core.chain.risk;


import com.lanhuigu.core.chain.risk.model.RiskAccessory;

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
