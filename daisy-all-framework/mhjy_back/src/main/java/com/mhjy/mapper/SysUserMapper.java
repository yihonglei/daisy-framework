package com.mhjy.mapper;

import com.mhjy.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SysUserMapper {
    /**
     * 根据用户名获取用户信息
     * @param username
     * @return
     */
    SysUser getUserByUserName(String username);

    SysUser getUserByToken(String token);

    SysUser getUserById(long id);

    int addSysUser(SysUser sysUser);

    int updateSysEnable(long id, int status, long updated_by, Date updated_at);

    int realDelByUserId(long id);

    int updateSysUser(SysUser sysUser);

    int resetSysPWD(long id, String password, long updated_by, Date updated_at);

    /**
     * 系统用户列表
     * @return
     */
    List<SysUser> usersList();



}
