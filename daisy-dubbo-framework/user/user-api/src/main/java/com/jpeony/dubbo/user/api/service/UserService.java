package com.jpeony.dubbo.user.api.service;

import com.jpeony.dubbo.commons.utils.ApiResponse;
import com.jpeony.dubbo.user.api.dto.UserDTO;

/**
 * @author yihonglei
 */
public interface UserService {
    /**
     * 用户查询
     */
    ApiResponse getUser(UserDTO userDTO);
}
