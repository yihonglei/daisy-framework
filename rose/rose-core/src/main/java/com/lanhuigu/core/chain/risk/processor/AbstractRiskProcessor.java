package com.lanhuigu.core.chain.risk.processor;

import com.lanhuigu.core.chain.risk.RiskProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象
 *
 * @author yihonglei
 * @date 2019/10/31 6:40 PM
 */
public abstract class AbstractRiskProcessor implements RiskProcessor {
    protected Logger logger = LoggerFactory.getLogger(getClass());
}
