package com.jpeony.cloud.user.server.service;

import com.jpeony.cloud.user.server.api.dto.UserInfoDTO;
import com.jpeony.cloud.user.server.api.vo.UserInfoVO;

/**
 * @author yihonglei
 */
public interface UserInfoService {
    UserInfoVO getUserInfo(UserInfoDTO userInfoDTO);
}
