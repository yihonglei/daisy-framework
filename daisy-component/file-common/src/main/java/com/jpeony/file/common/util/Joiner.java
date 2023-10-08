package com.jpeony.file.common.util;

import java.util.List;
import java.util.ListIterator;

/**
 * 接合器
 *
 * @author yihonglei
 */
public class Joiner {

    /**
     * 拼接
     *
     * @param list
     * @param separator
     * @return
     */
    public static String join(List<String> list, String separator) {
        ListIterator<String> it = list.listIterator();
        StringBuilder builder = new StringBuilder();
        if (it.hasNext()) {
            builder.append(it.next());
            while (it.hasNext()) {
                builder.append(separator);
                builder.append(it.next());
            }
        }
        return builder.toString();
    }
}
