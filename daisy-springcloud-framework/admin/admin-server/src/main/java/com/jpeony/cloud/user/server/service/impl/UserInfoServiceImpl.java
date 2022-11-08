package com.jpeony.cloud.user.server.service.impl;

import com.jpeony.cloud.user.server.api.dto.UserInfoReq;
import com.jpeony.cloud.user.server.api.vo.UserInfoRes;
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
    public UserInfoRes getUserInfo(UserInfoReq userInfoReq) {
        UserInfoRes userInfoRes = new UserInfoRes();
        UserInfoDO userInfoDO = userInfoMapper.queryUserInfo(userInfoReq.getUserId());
        if (userInfoDO == null) {
            userInfoDO = userInfoMapper.queryUserInfoMaster(userInfoReq.getUserId());
        }
        userInfoRes.setUserId(userInfoDO.getUserId());
        userInfoRes.setUserName(userInfoDO.getUserName());
        userInfoRes.setAge(userInfoDO.getAge());

        return userInfoRes;
    }
}
