package com.jpeony.pay.biz.abs;


import com.jpeony.commons.core.AbstractRequest;

/**
 * 数据验证接口类
 */
public interface Validator {
    /**
     * 数据验证
     */
    void validate(AbstractRequest request);
}
