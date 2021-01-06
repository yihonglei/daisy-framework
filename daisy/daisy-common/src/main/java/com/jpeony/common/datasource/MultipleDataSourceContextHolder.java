package com.jpeony.common.datasource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 数据源切换处理
 *
 * @author yihonglei
 */
public class MultipleDataSourceContextHolder {
    public static final Logger logger = LoggerFactory.getLogger(MultipleDataSourceContextHolder.class);
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源变量
     */
    public static void setDataSourceType(String dsType) {
        CONTEXT_HOLDER.set(dsType);
    }

    /**
     * 获得数据源变量
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
