package com.lanhuigu.core.chain.risk;

import com.google.common.collect.Lists;
import com.lanhuigu.common.pojo.vo.RiskVO;
import com.lanhuigu.core.chain.risk.model.Accessory;
import com.lanhuigu.core.chain.risk.model.RiskResult;

import java.util.List;

/**
 * 负责链条的添加和执行，链条内容为各个处理器
 *
 * @author yihonglei
 * @date 2019/10/31 5:33 PM
 */
public class DefaultRiskProcessorChain implements RiskProcessorChain {
    private List<RiskProcessor> filters = Lists.newArrayList();
    private int index = 0;

    @Override
    public RiskVO doProcess(Accessory accessory, RiskResult result) {
        if (index == filters.size()) {
            return result.getRiskVO();
        }

        filters.get(index++).doProcessor(accessory, result, this);

        return result.getRiskVO();
    }

    @Override
    public RiskProcessorChain addProcessorChain(RiskProcessor processor) {
        this.filters.add(processor);
        return this;
    }
}
