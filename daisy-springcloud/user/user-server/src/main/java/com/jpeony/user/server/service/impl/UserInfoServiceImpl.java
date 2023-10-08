package com.jpeony.user.server.service.impl;

import com.jpeony.user.server.api.dto.UserInfoDTO;
import com.jpeony.user.server.api.vo.UserInfoVO;
import com.jpeony.user.server.mapper.UserInfoMapper;
import com.jpeony.user.server.pojo.domain.UserInfoDO;
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
    public UserInfoVO getUserInfo(UserInfoDTO userInfoDTO) {
        UserInfoVO userInfoVO = new UserInfoVO();
        UserInfoDO userInfoDO = userInfoMapper.queryUserInfo(userInfoDTO.getUserId());
        // userInfoDO = null;  // 放开，测试查询主库
        if (userInfoDO == null) {
            userInfoDO = userInfoMapper.queryUserInfoMaster(userInfoDTO.getUserId());
        }
        userInfoVO.setUserId(userInfoDO.getUserId());
        userInfoVO.setUserName(userInfoDO.getUserName());
        userInfoVO.setAge(userInfoDO.getAge());

        return userInfoVO;
    }

    @Override
    public UserInfoVO getUserInfoDTO(UserInfoDTO userInfoDTO) {
        UserInfoVO userInfoVO = new UserInfoVO();
        UserInfoDO userInfoDO = userInfoMapper.queryUserInfoDTO(userInfoDTO);

        userInfoVO.setUserId(userInfoDO.getUserId());
        userInfoVO.setUserName(userInfoDO.getUserName());
        userInfoVO.setAge(userInfoDO.getAge());
        return userInfoVO;
    }
}
