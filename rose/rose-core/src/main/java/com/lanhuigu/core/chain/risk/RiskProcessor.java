package com.lanhuigu.core.chain.risk;


import com.lanhuigu.core.chain.risk.model.RiskAccessory;

/**
 * 风控过滤器
 *
 * @author yihonglei
 * @date 2019/10/31 4:44 PM
 */
public interface RiskProcessor {
    /**
     * 执行过滤
     *
     * @author yihonglei
     * @date 2019/10/31 5:43 PM
     */
    void doProcessor(RiskAccessory accessory, RiskProcessorChain chain);
}
