package com.lanhuigu.core.chain.risk;

import com.lanhuigu.common.pojo.vo.RiskVO;
import com.lanhuigu.core.chain.risk.model.Accessory;
import com.lanhuigu.core.chain.risk.model.RiskResult;

/**
 * 风险过滤链
 *
 * @author yihonglei
 * @date 2019/10/31 5:01 PM
 */
public interface RiskProcessorChain {
    /**
     * 筛选风险用户，如果是风险用户，则链条中断返回
     *
     * @author yihonglei
     * @date 2019/10/31 5:08 PM
     */
    RiskVO doProcess(Accessory accessory, RiskResult result);

    /**
     * 添加责任链
     *
     * @author yihonglei
     * @date 2019/10/31 5:12 PM
     */
    RiskProcessorChain addProcessorChain(RiskProcessor processor);

}
