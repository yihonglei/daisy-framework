package com.mhjy.service;

import com.mhjy.entity.SysUserRole;
import com.mhjy.mapper.SysUserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    public List<SysUserRole> userRolesWithUid(long uid) {
        return sysUserRoleMapper.userRoleWithUid(uid);
    }

    public Boolean delUserRolesWithUid(long uid) {
        return sysUserRoleMapper.delUserRolesWithUid(uid) >= 1;
    }

    public Boolean addSysUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.addSysUserRole(sysUserRole) == 1;
    }

}
