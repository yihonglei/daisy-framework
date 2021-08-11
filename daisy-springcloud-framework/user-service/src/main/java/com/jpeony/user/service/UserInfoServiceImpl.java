package com.jpeony.user.service;

import com.jpeony.feign.user.api.UserInfoService;
import com.jpeony.feign.user.api.pojo.dto.UserDTO;
import com.jpeony.feign.user.api.pojo.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public String getUserInfoByUserId(int userId) {

        return "hello, userService";
    }

    @Override
    public UserVO getUserInfo(UserDTO userDTO) {
        UserVO userVO = new UserVO();
        userVO.setUserId(1);
        userVO.setName(userDTO.getName());
        userVO.setAge(userDTO.getAge());

        return userVO;
    }
}
