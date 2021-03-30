package com.jpeony.user;

import com.jpeony.user.dto.UserRegisterRequest;
import com.jpeony.user.dto.UserRegisterResponse;

public interface IUserRegisterService {

    /**
     * 实现用户注册功能
     */
    UserRegisterResponse register(UserRegisterRequest request);
}
