package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.domain.SysUserRoleDO;

import java.util.List;

public interface SysUserRoleService {

    List<SysUserRoleDO> userRolesByUserId(long userId);

    Boolean delUserRolesByUserId(long userId);

    Boolean addSysUserRole(SysUserRoleDO sysUserRoleDO);
    
}
