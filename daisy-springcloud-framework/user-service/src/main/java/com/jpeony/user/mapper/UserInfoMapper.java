package com.jpeony.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jpeony.commons.datasource.annotation.DB;
import com.jpeony.commons.datasource.annotation.UseMaster;
import com.jpeony.user.pojo.domain.UserInfoDO;
import com.jpeony.user.constant.DBConstant;
import com.jpeony.user.pojo.dto.UserInfoDetailDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@DB(name = DBConstant.USER)
public interface UserInfoMapper extends BaseMapper<UserInfoDO> {

    /**
     * 单表从库查询返回 DO
     */
    @Select("select * from user_info where user_id = #{userId}")
    UserInfoDO queryUserInfo(@Param("userId") int userId);

    /**
     * 单表主库查询返回 DO
     */
    @UseMaster
    @Select("select * from user_info where user_id = #{userId}")
    UserInfoDO queryUserInfoMaster(@Param("userId") int userId);

    /**
     * 如果是多表关联查询返回 DTO
     */
    @UseMaster
    @Select("select * from user_info where user_name = #{userName}")
    UserInfoDetailDTO queryUserInfoDetail(@Param("userName") String userName);

    /**
     * 更新返回
     */
    @Update("update user_info set user_name = #{userName} where user_id = #{userId}")
    int updateUserInfo(@Param("userId") int userId, @Param("userName") String userName);
}
