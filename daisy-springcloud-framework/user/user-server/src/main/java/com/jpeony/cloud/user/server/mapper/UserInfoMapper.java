package com.jpeony.cloud.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jpeony.cloud.user.server.pojo.domain.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {

    UserInfoDO queryUserInfo(Integer userId);

    UserInfoDO queryUserInfoMaster(Integer userId);
}
