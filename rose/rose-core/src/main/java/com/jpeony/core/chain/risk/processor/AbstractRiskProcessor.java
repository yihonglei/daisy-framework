package com.jpeony.core.chain.risk.processor;

import com.jpeony.core.chain.risk.RiskProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象
 *
 * @author yihonglei
 */
public abstract class AbstractRiskProcessor implements RiskProcessor {
    Logger logger = LoggerFactory.getLogger(getClass());
}
