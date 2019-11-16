package com.lanhuigu.common.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;

/**
 * 自定义logback tab(\t)
 *
 * @author yihonglei
 * @date 2019/11/16 2:41 PM
 */
public class LogTab extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return String.valueOf(CoreConstants.TAB);
    }
}
