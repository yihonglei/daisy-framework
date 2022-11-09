package com.jpeony.cloud.user.server.service.impl;

import com.jpeony.cloud.user.server.api.dto.UserInfoDTO;
import com.jpeony.cloud.user.server.api.vo.UserInfoVO;
import com.jpeony.cloud.user.server.mapper.UserInfoMapper;
import com.jpeony.cloud.user.server.pojo.domain.UserInfoDO;
import com.jpeony.cloud.user.server.service.UserInfoService;
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
    public UserInfoVO getUserInfo(UserInfoDTO userInfoDTO) {
        UserInfoVO userInfoRes = new UserInfoVO();
        UserInfoDO userInfoDO = userInfoMapper.queryUserInfo(userInfoDTO.getUserId());
        if (userInfoDO == null) {
            userInfoDO = userInfoMapper.queryUserInfoMaster(userInfoDTO.getUserId());
        }
        userInfoRes.setUserId(userInfoDO.getUserId());
        userInfoRes.setUserName(userInfoDO.getUserName());
        userInfoRes.setAge(userInfoDO.getAge());

        return userInfoRes;
    }
}
