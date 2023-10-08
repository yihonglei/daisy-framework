package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysUserDO;

import java.util.Date;
import java.util.List;

public interface SysUserMapper {

    SysUserDO getUserByUserName(String username);

    SysUserDO getUserByToken(String token);

    SysUserDO getUserById(long id);

    int addSysUser(SysUserDO sysUserDO);

    int updateSysEnable(long id, int status, long updatedBy, Date updatedAt);

    int realDelByUserId(long id);

    int updateSysUser(SysUserDO sysUserDO);

    int resetSysPWD(long id, String password, long updatedBy, Date updatedAt);

    List<SysUserDO> usersList();

}
