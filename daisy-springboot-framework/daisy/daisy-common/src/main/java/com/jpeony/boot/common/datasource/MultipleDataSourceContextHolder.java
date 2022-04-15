package com.jpeony.boot.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yihonglei
 */
public class MultipleDataSourceContextHolder {
    public static final Logger logger = LoggerFactory.getLogger(MultipleDataSourceContextHolder.class);
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    public static void setDataSourceType(String dsType) {
        CONTEXT_HOLDER.remove();
        CONTEXT_HOLDER.set(dsType);
    }

    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
