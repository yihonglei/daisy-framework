package com.jpeony.search;

import com.jpeony.commons.tool.exception.BaseBusinessException;

/**
 * 搜索模块异常处理
 */
public class SearchException extends BaseBusinessException {
    public SearchException() {
    }

    public SearchException(String errorCode) {
        super(errorCode);
    }

    public SearchException(String errorCode, String message) {
        super(errorCode, message);
    }

    public static SearchException create(String errorCode, String message) {
        return new SearchException(errorCode, message);
    }
}
