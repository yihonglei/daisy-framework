package com.jpeony.dubbo.user.client.api;

import com.jpeony.dubbo.common.utils.ApiResponse;
import com.jpeony.dubbo.user.client.api.pojo.dto.UserDTO;

/**
 * @author yihonglei
 */
public interface UserService {
    /**
     * 用户查询
     */
    ApiResponse getUser(UserDTO userDTO);
}
