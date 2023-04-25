package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysUser;

import java.util.Date;
import java.util.List;

public interface SysUserMapper {

    SysUser getUserByUserName(String username);

    SysUser getUserByToken(String token);

    SysUser getUserById(long id);

    int addSysUser(SysUser sysUser);

    int updateSysEnable(long id, int status, long updated_by, Date updated_at);

    int realDelByUserId(long id);

    int updateSysUser(SysUser sysUser);

    int resetSysPWD(long id, String password, long updated_by, Date updated_at);

    List<SysUser> usersList();

}
