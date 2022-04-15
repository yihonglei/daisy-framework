package com.jpeony.user.server.service;

import com.jpeony.user.api.req.UserInfoReq;
import com.jpeony.user.api.res.UserInfoRes;

/**
 * @author yihonglei
 */
public interface UserInfoService {
    UserInfoRes getUserInfo(UserInfoReq userInfoReq);
}
