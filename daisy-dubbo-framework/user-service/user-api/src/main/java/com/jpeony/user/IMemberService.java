package com.jpeony.user;

import com.jpeony.user.dto.*;
import com.jpeony.user.dto.*;

/**
 * 会员服务
 */
public interface IMemberService {

    /**
     * 根据用户id查询用户会员信息
     */
    QueryMemberResponse queryMemberById(QueryMemberRequest request);

    /**
     * 修改用户头像
     */
    HeadImageResponse updateHeadImage(HeadImageRequest request);

    /**
     * 更新信息
     */
    UpdateMemberResponse updateMember(UpdateMemberRequest request);
}
