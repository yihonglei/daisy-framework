package com.lanhuigu.core.chain.risk;

import com.google.common.collect.Lists;
import com.lanhuigu.common.pojo.vo.RiskVO;
import com.lanhuigu.core.chain.risk.model.RiskAccessory;

import java.util.List;

/**
 * 负责链条的添加和执行，链条内容为各个处理器
 *
 * @author yihonglei
 */
public class DefaultRiskProcessorChain implements RiskProcessorChain {
    private List<RiskProcessor> filters = Lists.newArrayList();
    private int index = 0;

    @Override
    public RiskVO doProcess(RiskAccessory accessory) {
        if (index == filters.size()) {
            return accessory.getRiskVO();
        }

        filters.get(index++).doProcessor(accessory, this);

        return accessory.getRiskVO();
    }

    @Override
    public void addProcessorChain(RiskProcessor processor) {
        this.filters.add(processor);
    }
}
