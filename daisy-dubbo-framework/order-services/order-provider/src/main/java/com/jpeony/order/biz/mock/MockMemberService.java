package com.jpeony.order.biz.mock;

import com.jpeony.user.IMemberService;
import com.jpeony.user.dto.*;

/**
 * 会员信息 mock 类
 **/
public class MockMemberService implements IMemberService {
    @Override
    public QueryMemberResponse queryMemberById(QueryMemberRequest request) {
        return new QueryMemberResponse();
    }

    @Override
    public HeadImageResponse updateHeadImage(HeadImageRequest request) {
        return new HeadImageResponse();
    }

    @Override
    public UpdateMemberResponse updateMember(UpdateMemberRequest request) {
        return new UpdateMemberResponse();
    }
}
