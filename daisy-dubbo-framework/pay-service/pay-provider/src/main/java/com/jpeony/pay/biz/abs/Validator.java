package com.jpeony.pay.biz.abs;


import com.jpeony.commons.result.AbstractRequest;

/**
 * 数据验证接口类
 */
public interface Validator {
    /**
     * 数据验证
     * @param request
     */
    void validate(AbstractRequest request);
}
