package com.jpeony.cloud.user.server.service;

import com.jpeony.cloud.user.server.api.req.UserInfoReq;
import com.jpeony.cloud.user.server.api.res.UserInfoRes;

/**
 * @author yihonglei
 */
public interface UserInfoService {
    UserInfoRes getUserInfo(UserInfoReq userInfoReq);
}
