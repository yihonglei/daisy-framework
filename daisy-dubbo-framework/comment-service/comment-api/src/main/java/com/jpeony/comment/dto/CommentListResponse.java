package com.jpeony.comment.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

/**
 * 分页查询单个商品的商品评价返回类型
 */
@Data
public class CommentListResponse extends AbstractResponse {

    private List<CommentDto> commentDtoList;

    private int page;

    private int size;

    private long total;
}
