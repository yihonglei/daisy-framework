package com.jpeony.comment.dto;

import com.jpeony.comment.constant.CommentRetCode;
import com.jpeony.commons.result.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 商品评价审核请求参数
 */
@Data
public class AuditCommentRequest extends AbstractRequest {

    /**
     * 商品评价id
     */
    private String commentId;

    /**
     * 审核是否通过
     */
    private boolean isValid;

    /**
     * 审核人id
     */
    private Long validationUserId;

    /**
     * 审核意见
     */
    private String validationSuggestion;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(commentId) || validationUserId == null) {
            throw new ValidateException(CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(), CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
        if (!isValid && StringUtils.isEmpty(validationSuggestion)) {
            // 审核不通过时必须填写审核意见
            throw new ValidateException(CommentRetCode.REQUEST_PARAMETER_ERROR.getCode(), CommentRetCode.REQUEST_PARAMETER_ERROR.getMessage());
        }
    }
}
