package com.jpeony.boot.common.logback;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;

/**
 * 自定义logback tab
 *
 * @author yihonglei
 */
public class LogTab extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent event) {
        return String.valueOf(CoreConstants.TAB);
    }
}
