package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.domain.SysUserRole;

import java.util.List;

public interface SysUserRoleService {
    List<SysUserRole> userRolesByUserId(long userId);

    Boolean delUserRolesByUserId(long userId);

    Boolean addSysUserRole(SysUserRole sysUserRole);
}
