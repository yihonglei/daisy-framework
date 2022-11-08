package com.jpeony.cloud.user.server.service;

import com.jpeony.cloud.user.server.api.dto.UserInfoReq;
import com.jpeony.cloud.user.server.api.vo.UserInfoRes;

/**
 * @author yihonglei
 */
public interface UserInfoService {
    UserInfoRes getUserInfo(UserInfoReq userInfoReq);
}
