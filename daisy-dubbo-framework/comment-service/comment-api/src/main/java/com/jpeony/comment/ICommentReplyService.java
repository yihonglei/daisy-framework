package com.jpeony.comment;

import com.jpeony.comment.dto.*;
import com.jpeony.comment.dto.*;

/**
 * 商品评价回复服务接口
 */
public interface ICommentReplyService {

    /**
     * 新增商品评价回复
     */
    AddCommentReplyResponse addCommentReply(AddCommentReplyRequest request);

    /**
     * 删除商品评价回复
     */
    DeleteCommentReplyResponse deleteCommentReply(DeleteCommentReplyRequest request);

    /**
     * 分页查询商品评价回复意见
     */
    CommentReplyListResponse commentReplyList(CommentReplyListRequest request);
}
