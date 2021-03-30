package com.jpeony.comment.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

/**
 * 根据订单详情id查看评价返回结果
 */
@Data
public class CommentResponse extends AbstractResponse {

    private List<CommentDto> commentDtoList;
}
