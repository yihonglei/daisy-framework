package com.jpeony.user.server.service;

import com.jpeony.user.api.request.UserInfoParam;
import com.jpeony.user.api.response.UserInfoDTO;

/**
 * @author yihonglei
 */
public interface UserInfoService {
    String getUserInfoByUserId(int userId);

    UserInfoDTO getUserInfo(UserInfoParam userInfoParam);
}
