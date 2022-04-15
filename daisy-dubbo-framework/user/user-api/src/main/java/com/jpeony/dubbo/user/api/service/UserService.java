package com.jpeony.dubbo.user.api.service;

import com.jpeony.dubbo.commons.core.utils.ApiResponse;
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
