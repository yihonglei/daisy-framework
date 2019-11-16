package com.lanhuigu.common.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

import java.util.concurrent.atomic.LongAdder;

/**
 * 自定义logback自增数字
 *
 * @author yihonglei
 * @date 2019/11/16 2:41 PM
 */
public class LogSort extends ClassicConverter {
    private static LongAdder adder = new LongAdder();

    @Override
    public String convert(ILoggingEvent loggingEvent) {
        adder.increment();
        return adder.longValue() + "";
    }

}
