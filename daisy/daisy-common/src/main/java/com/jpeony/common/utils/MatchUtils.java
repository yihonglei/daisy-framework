package com.jpeony.common.utils;

import java.util.regex.Pattern;

/**
 * @author yihonglei
 */
public class MatchUtils {
    public static final String PATTERN_MATCH_MASTER = "_MASTER";
    public static final String PATTERN_MATCH_SLAVE = "_SLAVE.*";

    public static boolean matchDataSource(String match, String dataSourceType) {
        return Pattern.matches(match, dataSourceType);
    }
}
