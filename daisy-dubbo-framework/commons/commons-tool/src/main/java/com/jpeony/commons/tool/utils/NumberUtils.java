package com.jpeony.commons.tool.utils;

import java.math.BigDecimal;

public class NumberUtils {

    /**
     * 保留二位小数
     */
    public static double toDouble(BigDecimal data) {
        return data.setScale(2, BigDecimal.ROUND_DOWN).doubleValue();
    }
}
