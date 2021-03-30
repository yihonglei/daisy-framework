package com.jpeony.coupon.dto;

import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import com.jpeony.coupon.SaleTypeEnum;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class CreateActiRequest extends AbstractRequest {
    private String name;
    private SaleTypeEnum saleType;
    private String desc;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(name)) {
            throw new ValidateException("缺少name参数");
        }
        if (saleType == null) {
            throw new ValidateException("缺少type参数");
        }
    }
}
