package com.jpeony.boot.core.chain.demo.processor;

import com.jpeony.boot.core.chain.demo.DemoProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象
 *
 * @author yihonglei
 */
public abstract class AbstractDemoProcessor implements DemoProcessor {
    Logger logger = LoggerFactory.getLogger(getClass());
}
