package com.mhjy.mapper;

import com.mhjy.entity.SysRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMapper {
    SysRole roleWithId(long roleId);
    SysRole roleWithRolename(String rolename);
    List<SysRole> allRoles();

    int addSysRole(SysRole sysRole);
    int updateSysRole(SysRole sysRole);
    int delByRoleId(long roleId);
}
