package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysRoleDO;

import java.util.List;

public interface SysRoleMapper {

    SysRoleDO roleById(long roleId);

    SysRoleDO roleByRoleName(String roleName);

    List<SysRoleDO> allRoles();

    int addSysRole(SysRoleDO sysRoleDO);

    int updateSysRole(SysRoleDO sysRoleDO);

    int delByRoleId(long roleId);

}
