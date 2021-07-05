package com.jpeony.dubbo.user.server.services;

import com.jpeony.dubbo.common.utils.ApiResponse;
import com.jpeony.dubbo.user.client.api.UserService;
import com.jpeony.dubbo.user.client.api.pojo.dto.UserDTO;
import com.jpeony.dubbo.user.client.api.pojo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service // dubbo 的 service 注解，发布服务，不是 spring 的 service 注解
public class UserServiceImpl implements UserService {

    @Override
    public ApiResponse getUser(UserDTO userDTO) {
        UserVO userVO = new UserVO();
        userVO.setId(userDTO.getId());
        userVO.setUserName("user");
        userVO.setSex(1);
        return ApiResponse.success(userVO);
    }
}
