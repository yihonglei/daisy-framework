package com.lanhuigu.core.chain.risk;


import com.lanhuigu.core.chain.risk.model.Accessory;
import com.lanhuigu.core.chain.risk.model.RiskResult;

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
    void doProcessor(Accessory accessory, RiskResult result, RiskProcessorChain chain);
}
