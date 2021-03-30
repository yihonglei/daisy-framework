package com.jpeony.comment.dto;

import com.jpeony.comment.constant.CommentRetCode;
import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 根据评价计算商品综合评分参数
 */
@Data
public class ItemScoreRequest extends AbstractRequest {

    /**
     * 商品id
     */
    private String itemId;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(itemId)) {
            throw new ValidateException(CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(), CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
    
}
