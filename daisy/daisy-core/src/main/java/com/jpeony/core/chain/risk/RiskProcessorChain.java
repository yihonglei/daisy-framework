package com.jpeony.core.chain.risk;

import com.jpeony.common.pojo.vo.RiskVO;
import com.jpeony.core.chain.risk.model.RiskAccessory;

/**
 * 风险过滤链
 *
 * @author yihonglei
 */
public interface RiskProcessorChain {
    /**
     * 筛选风险用户，如果是风险用户，则链条中断返回
     */
    RiskVO doProcess(RiskAccessory accessory);

    /**
     * 添加责任链
     */
    void addProcessorChain(RiskProcessor processor);

}
