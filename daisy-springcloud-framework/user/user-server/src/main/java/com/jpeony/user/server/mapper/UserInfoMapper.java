package com.jpeony.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jpeony.user.server.api.dto.UserInfoDTO;
import com.jpeony.user.server.pojo.domain.UserInfoDO;
import com.jpeony.commons.datasource.annotation.UseMaster;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {

    /**
     * 一个参数-从库
     */
    UserInfoDO queryUserInfo(Integer userId);

    /**
     * 一个参数-主库
     */
    @UseMaster
    UserInfoDO queryUserInfoMaster(Integer userId);

    /**
     * 多个参数-从库
     */
    UserInfoDO queryUserInfoDTO(UserInfoDTO userInfoDTO);
}
