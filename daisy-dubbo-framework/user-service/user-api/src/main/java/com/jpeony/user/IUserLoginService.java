package com.jpeony.user;

import com.jpeony.user.dto.CheckAuthRequest;
import com.jpeony.user.dto.CheckAuthResponse;
import com.jpeony.user.dto.UserLoginRequest;
import com.jpeony.user.dto.UserLoginResponse;

public interface IUserLoginService {

    /**
     * 用户登录
     * @param request
     * @return
     */
    UserLoginResponse login(UserLoginRequest request);


    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);
}
