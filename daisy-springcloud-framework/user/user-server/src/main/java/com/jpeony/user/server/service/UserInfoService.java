package com.jpeony.user.server.service;

import com.jpeony.user.api.request.UserInfoReq;
import com.jpeony.user.api.response.UserInfoRes;

/**
 * @author yihonglei
 */
public interface UserInfoService {
    String getUserInfoByUserId(int userId);

    UserInfoRes getUserInfo(UserInfoReq userInfoReq);
}
