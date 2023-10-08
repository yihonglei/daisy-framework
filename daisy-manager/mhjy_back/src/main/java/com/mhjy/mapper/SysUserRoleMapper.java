package com.mhjy.mapper;

import com.mhjy.entity.SysUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysUserRoleMapper {

    List<SysUserRole> userRoleWithUid(long uid);

    int addSysUserRole(SysUserRole sysUserRole);

    int delUserRolesWithUid(long uid);
}
