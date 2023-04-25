package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.bo.SysRoleBO;
import com.jpeony.lotus.core.pojo.domain.SysRole;
import com.jpeony.lotus.core.pojo.dto.SysRoleDto;

import java.util.List;

public interface SysRoleService {
    SysRole roleWithId(long roleId);

    List<SysRole> allRolesIgnore();

    List<SysRoleBO> allRoles();

    SysRoleBO addRole(String token, SysRoleDto sysRoleDto) throws Exception;

    SysRoleBO editRole(String token, SysRoleDto sysRoleDto) throws Exception;

    Boolean delRole(String token, SysRoleDto sysRoleDto) throws Exception;
}
