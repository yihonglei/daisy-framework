package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysUserRoleDO;

import java.util.List;

public interface SysUserRoleMapper {

    List<SysUserRoleDO> userRoleByUserId(long userId);

    int addSysUserRole(SysUserRoleDO sysUserRoleDO);

    int delUserRolesByUserId(long userId);
    
}
