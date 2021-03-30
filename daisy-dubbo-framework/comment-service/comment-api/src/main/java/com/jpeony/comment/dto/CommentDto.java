package com.jpeony.comment.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品评价
 */
@Data
public class CommentDto implements Serializable {

    private String id;

    private String orderId;

    private String itemId;

    private Byte star;

    private Byte type;

    private Boolean isAnoymous;

    private String content;

    private String buyerNick;

    private Date commentTime;

    private Boolean isPublic;

    private Boolean isValid;

    private Long validationUserId;

    private Date validationTime;

    private String validationSuggestion;

    private Boolean isTop;

    private Long userId;

    private Boolean isDeleted;

    private Date deletionTime;

    private Long deletionUserId;
    
}
