package com.jpeony.user.service;

import com.jpeony.feign.user.api.UserInfoService;
import com.jpeony.user.pojo.domain.UserInfoDO;
import com.jpeony.feign.user.api.request.UserInfoParam;
import com.jpeony.feign.user.api.response.UserInfoDTO;
import com.jpeony.user.mapper.UserInfoMapper;
import com.jpeony.user.pojo.dto.UserInfoDetailDTO;
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
        UserInfoDetailDTO userInfoDetailDTO = userInfoMapper.queryUserInfoDetail(userInfoParam.getUserName());
        // info2 ..
        // 组合返回
        userInfoDTO.setUserId(userInfoDetailDTO.getUserId());
        userInfoDTO.setUserName(userInfoDetailDTO.getUserName());
        userInfoDTO.setAge(userInfoDetailDTO.getAge());

        return userInfoDTO;
    }
}
