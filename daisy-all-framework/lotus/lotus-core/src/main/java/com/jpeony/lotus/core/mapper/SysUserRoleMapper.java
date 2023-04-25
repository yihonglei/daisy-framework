package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysUserRole;

import java.util.List;

public interface SysUserRoleMapper {

    List<SysUserRole> userRoleByUserId(long userId);

    int addSysUserRole(SysUserRole sysUserRole);

    int delUserRolesByUserId(long userId);
}
