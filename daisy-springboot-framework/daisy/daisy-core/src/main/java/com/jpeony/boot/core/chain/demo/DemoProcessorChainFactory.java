package com.jpeony.boot.core.chain.demo;

import com.google.common.collect.Maps;
import com.jpeony.boot.core.pojo.vo.RiskVO;
import com.jpeony.boot.common.utils.SpringBeanUtils;
import com.jpeony.boot.core.chain.demo.model.DemoAccessory;
import com.jpeony.boot.core.chain.demo.model.DemoContext;
import com.jpeony.boot.core.chain.demo.processor.OneHandlerProcessor;
import com.jpeony.boot.core.chain.demo.processor.TwoHandlerProcessor;
import com.jpeony.boot.core.chain.demo.processor.ThreeHandlerProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * 责任链工厂
 *
 * @author yihonglei
 */
public class DemoProcessorChainFactory {
    private static final Logger logger = LoggerFactory.getLogger(DemoProcessorChainFactory.class);

    private static final Map<String, DemoProcessor> BEAN_MAP;

    static {
        Map<String, DemoProcessor> beanMap = SpringBeanUtils.beansOfTypeIncludingAncestors(DemoProcessor.class);
        BEAN_MAP = Maps.newHashMapWithExpectedSize(beanMap.size());
        beanMap.forEach((k, v) -> BEAN_MAP.put(v.getClass().getName(), v));
    }

    public static RiskVO invokeProcess(DemoContext context) {
        DemoProcessorChain chain = new DefaultDemoProcessorChain();

        addProcessorChain(chain, OneHandlerProcessor.class);
        addProcessorChain(chain, TwoHandlerProcessor.class);
        addProcessorChain(chain, ThreeHandlerProcessor.class);

        // 封装存储器
        DemoAccessory<RiskVO> accessory = new DemoAccessory<>();
        accessory.setDemoContext(context);
        accessory.setResult(new RiskVO());

        return (RiskVO) chain.doProcess(accessory);
    }

    /**
     * 添加责任链
     */
    private static void addProcessorChain(DemoProcessorChain chain, Class<? extends DemoProcessor> type) {
        DemoProcessor demoProcessor = getDemoProcessor(type);
        Assert.notNull(demoProcessor, "RiskProcessor must not be null,type=" + type.getName());
        chain.addProcessorChain(demoProcessor);
    }

    /**
     * 获取processor
     */
    private static DemoProcessor getDemoProcessor(Class<? extends DemoProcessor> type) {
        return BEAN_MAP.get(type.getName());
    }

}
