package com.jpeony.commons.core.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.concurrent.atomic.LongAdder;

/**
 * 自定义 logback 自增数字
 *
 * @author yihonglei
 */
public class LogSort extends ClassicConverter {
    private static LongAdder adder = new LongAdder();

    @Override
    public String convert(ILoggingEvent loggingEvent) {
        adder.increment();
        return adder.longValue() + "";
    }

}
