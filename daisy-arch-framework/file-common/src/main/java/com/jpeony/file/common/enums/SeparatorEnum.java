package com.jpeony.file.common.enums;

import lombok.Getter;

/**
 * 分隔符
 *
 * @author yihonglei
 */
public enum SeparatorEnum {
    DEFAULT(""),
    COMMA(","),
    SEMICOLON(";"),
    SPACE(" "),
    TAB("\t"),
    NEW_LINE_WIN("\r\n"),
    NEW_LINE_LINUX("\n"),
    ;

    SeparatorEnum(String separator) {
        this.separator = separator;
    }

    /**
     * 分隔符
     */
    @Getter
    private String separator;
}
