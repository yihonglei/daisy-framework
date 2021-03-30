package com.jpeony.comment;

import com.jpeony.comment.dto.*;
import com.jpeony.comment.dto.*;

/**
 * 商品评价服务接口
 */
public interface ICommentService {

    /**
     * 添加商品评价
     */
    AddCommentResponse addComment(AddCommentRequest request);

    /**
     * 根据订单详情id查询评价
     */
    CommentResponse comment(CommentRequest request);

    /**
     * 分页查询某个商品的评价
     */
    CommentListResponse commentList(CommentListRequest request);

    /**
     * 查询某个商品的评价总数
     */
    TotalCommentResponse totalComment(TotalCommentRequest request);

    /**
     * 删除评价
     */
    DeleteCommentResponse deleteComment(DeleteCommentRequest request);

    /**
     * 将商品评价置顶
     */
    TopCommentResponse topComment(TopCommentRequest request);

    /**
     * 商品评价审核
     */
    AuditCommentResponse auditComment(AuditCommentRequest request);

    /**
     * 根据商品评价计算综合评分
     */
    ItemScoreResponse itemScore(ItemScoreRequest request);
}
