package com.jpeony.comment.dto;

import com.jpeony.comment.constant.CommentRetCode;
import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 根据订单详情id查看评价请求参数
 */
@Data
public class CommentRequest extends AbstractRequest {

    private String orderItemId;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(orderItemId)) {
            throw new ValidateException(CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(), CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }

}
