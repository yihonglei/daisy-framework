package com.jpeony.user.server.service.impl;

import com.jpeony.user.api.request.UserInfoParam;
import com.jpeony.user.api.response.UserInfoDTO;
import com.jpeony.user.server.mapper.UserInfoMapper;
import com.jpeony.user.server.pojo.domain.UserInfoDO;
import com.jpeony.user.server.pojo.dto.UserInfoDetailDTO;
import com.jpeony.user.server.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yihonglei
 */
@Slf4j
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public String getUserInfoByUserId(int userId) {
        UserInfoDO userInfoDO = userInfoMapper.queryUserInfo(userId);
        if (userInfoDO == null) {
            userInfoDO = userInfoMapper.queryUserInfoMaster(userId);
        }

        return userInfoDO.getUserName();
    }

    @Override
    public UserInfoDTO getUserInfo(UserInfoParam userInfoParam) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        // info1
        UserInfoDetailDTO userInfoDetailDTO = userInfoMapper.queryUserInfoDetailByUserId(userInfoParam.getUserId());
        // info2 ..
        // 组合返回
        userInfoDTO.setUserId(userInfoDetailDTO.getUserId());
        userInfoDTO.setUserName(userInfoDetailDTO.getUserName());
        userInfoDTO.setAge(userInfoDetailDTO.getAge());

        return userInfoDTO;
    }
}
