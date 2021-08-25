package com.jpeony.user.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jpeony.commons.datasource.annotation.DB;
import com.jpeony.commons.datasource.annotation.UseMaster;
import com.jpeony.user.server.constant.DBConstant;
import com.jpeony.user.server.pojo.domain.UserInfoDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@DB(name = DBConstant.USER)
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {

    /**
     * 单表从库查询返回 DO
     */
    @Select("select * from user_info where user_id = #{userId}")
    UserInfoDO queryUserInfo(@Param("userId") Integer userId);

    /**
     * 单表主库查询返回 DO
     */
    @UseMaster
    @Select("select * from user_info where user_id = #{userId}")
    UserInfoDO queryUserInfoMaster(@Param("userId") Integer userId);
}
