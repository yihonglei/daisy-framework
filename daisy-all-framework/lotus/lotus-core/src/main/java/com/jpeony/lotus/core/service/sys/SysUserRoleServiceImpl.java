package com.jpeony.lotus.core.service.sys;

import com.jpeony.lotus.core.mapper.SysUserRoleMapper;
import com.jpeony.lotus.core.pojo.domain.SysUserRole;
import com.jpeony.lotus.core.service.SysUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;

    @Override
    public List<SysUserRole> userRolesByUserId(long userId) {
        return sysUserRoleMapper.userRoleByUserId(userId);
    }

    @Override
    public Boolean delUserRolesByUserId(long userId) {
        return sysUserRoleMapper.delUserRolesByUserId(userId) >= 1;
    }

    @Override
    public Boolean addSysUserRole(SysUserRole sysUserRole) {
        return sysUserRoleMapper.addSysUserRole(sysUserRole) == 1;
    }

}
