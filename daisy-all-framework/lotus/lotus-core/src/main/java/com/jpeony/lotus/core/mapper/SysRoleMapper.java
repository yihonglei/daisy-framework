package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysRole;

import java.util.List;

public interface SysRoleMapper {
    
    SysRole roleWithId(long roleId);

    SysRole roleWithRolename(String rolename);

    List<SysRole> allRoles();

    int addSysRole(SysRole sysRole);

    int updateSysRole(SysRole sysRole);

    int delByRoleId(long roleId);

}
