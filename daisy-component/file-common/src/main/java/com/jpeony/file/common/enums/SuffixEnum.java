package com.jpeony.file.common.enums;

import lombok.Getter;

/**
 * 文件后缀
 *
 * @author yihonglei
 */
public enum SuffixEnum {
    DEFAULT(""),
    TXT(".txt"),
    CSV(".csv"),
    EXCEL(".xlsx"),
    SQL(".sql");

    SuffixEnum(String suffix) {
        this.suffix = suffix;
    }

    @Getter
    private String suffix;

}
