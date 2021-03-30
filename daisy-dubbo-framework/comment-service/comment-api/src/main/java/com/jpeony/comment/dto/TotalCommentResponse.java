package com.jpeony.comment.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

@Data
public class TotalCommentResponse extends AbstractResponse {

    private long total;
}
