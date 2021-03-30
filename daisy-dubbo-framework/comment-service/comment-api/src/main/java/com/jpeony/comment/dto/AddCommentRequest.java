package com.jpeony.comment.dto;

import com.jpeony.comment.constant.CommentRetCode;
import com.jpeony.commons.core.AbstractRequest;
import com.jpeony.commons.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 添加商品评价请求参数
 */
@Data
public class AddCommentRequest extends AbstractRequest {

    private String orderItemId;

    private Integer star;

    private Integer type;

    private Boolean isAnoymous;

    private String content;

    private List<String> picPaths;

    private Boolean isPublic;

    @Override
    public void requestCheck() {
        if (StringUtils.isEmpty(orderItemId) || StringUtils.isEmpty(content) || isPublic == null) {
            throw new ValidateException(CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(), CommentRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
    
}
