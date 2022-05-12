package com.jpeony.file.common.util;

import static com.jpeony.file.common.constant.FileConstant.BACKSLASH;
import static com.jpeony.file.common.constant.FileConstant.BACKSLASH_CH;
import static com.jpeony.file.common.constant.FileConstant.DOUBLE_SEPARATOR;
import static com.jpeony.file.common.constant.FileConstant.SINGLE_SEPARATOR;

/**
 * 字符串操作
 *
 * @author yihonglei
 */
public class StringUtils {

    /**
     * Check that the given {@code String} is neither {@code null} nor of length 0.
     * <p>Note: this method returns {@code true} for a {@code String} that
     * purely consists of whitespace.
     *
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null} and has length
     * @see #hasLength(String)
     */
    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }

    /**
     * 获取文件名
     *
     * @param name     文件名
     * @param filePath 文件路径
     * @return
     */
    public static String getFileName(String filePath, String name) {
        String path = filePath;
        //将 \ 转换为 /
        if (path.contains(SINGLE_SEPARATOR)) {
            path = path.replace(SINGLE_SEPARATOR, BACKSLASH);
        }
        //将 \\ 转换为 /
        if (path.contains(DOUBLE_SEPARATOR)) {
            path = path.replace(DOUBLE_SEPARATOR, BACKSLASH);
        }
        //最后一位如果是 / 则去掉
        if (path.charAt(path.length() - 1) == BACKSLASH_CH) {
            path = path.substring(0, path.length() - 1);
        }
        return path + BACKSLASH + name;
    }
}
