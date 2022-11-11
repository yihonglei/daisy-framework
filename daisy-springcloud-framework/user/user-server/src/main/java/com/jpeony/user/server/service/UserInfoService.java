package com.jpeony.user.server.service;

import com.jpeony.user.server.api.dto.UserInfoDTO;
import com.jpeony.user.server.api.vo.UserInfoVO;

/**
 * @author yihonglei
 */
public interface UserInfoService {
    UserInfoVO getUserInfo(UserInfoDTO userInfoDTO);

    UserInfoVO getUserInfoDTO(UserInfoDTO userInfoDTO);
}
