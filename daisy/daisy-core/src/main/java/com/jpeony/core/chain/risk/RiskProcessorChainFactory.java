package com.jpeony.core.chain.risk;

import com.google.common.collect.Maps;
import com.jpeony.core.pojo.vo.RiskVO;
import com.jpeony.common.utils.SpringBeanUtils;
import com.jpeony.core.chain.risk.model.RiskAccessory;
import com.jpeony.core.chain.risk.model.RiskContext;
import com.jpeony.core.chain.risk.processor.HelpCallCarHandlerProcessor;
import com.jpeony.core.chain.risk.processor.UnfinishedHandlerProcessor;
import com.jpeony.core.chain.risk.processor.UserTypeHandlerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 责任链工厂
 *
 * @author yihonglei
 */
public class RiskProcessorChainFactory {
    private static final Logger logger = LoggerFactory.getLogger(RiskProcessorChainFactory.class);

    private static final Map<String, RiskProcessor> BEAN_MAP;

    static {
        Map<String, RiskProcessor> beanMap = SpringBeanUtils.beansOfTypeIncludingAncestors(RiskProcessor.class);
        BEAN_MAP = Maps.newHashMapWithExpectedSize(beanMap.size());
        beanMap.forEach((k, v) -> BEAN_MAP.put(v.getClass().getName(), v));
    }

    public static RiskVO riskProcess(RiskContext context) {
        RiskProcessorChain chain = new DefaultRiskProcessorChain();

        // 用户类型
        addProcessorChain(chain, UserTypeHandlerProcessor.class);
        // 代叫车
        addProcessorChain(chain, HelpCallCarHandlerProcessor.class);
        // 未完成订单
        addProcessorChain(chain, UnfinishedHandlerProcessor.class);
        // 封装存储器
        RiskAccessory<String> accessory = new RiskAccessory<>();
        accessory.setRiskContext(context);

        return chain.doProcess(accessory);
    }

    /**
     * 添加责任链
     */
    private static void addProcessorChain(RiskProcessorChain chain, Class<? extends RiskProcessor> type) {
        RiskProcessor riskProcessor = getRiskProcessor(type);
        Assert.notNull(riskProcessor, "RiskProcessor must not be null,type=" + type.getName());
        chain.addProcessorChain(riskProcessor);
    }

    /**
     * 获取processor
     */
    private static RiskProcessor getRiskProcessor(Class<? extends RiskProcessor> type) {
        return BEAN_MAP.get(type.getName());
    }

}
