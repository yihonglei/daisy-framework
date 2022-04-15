package com.jpeony.dubbo.user.provider.service;

import com.jpeony.dubbo.commons.core.utils.ApiResponse;
import com.jpeony.dubbo.user.api.service.UserService;
import com.jpeony.dubbo.user.api.dto.UserDTO;
import com.jpeony.dubbo.user.provider.pojo.vo.UserVO;
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
