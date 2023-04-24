package com.mhjy.service;

import com.mhjy.entity.SysRoleMenu;
import com.mhjy.mapper.SysRoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    public List<SysRoleMenu> rolesWithMenuId(long menuId) {
        return sysRoleMenuMapper.rolesWithMenuId(menuId);
    }

    public void delByRoleId(long roleId) {
        sysRoleMenuMapper.delByRoleId(roleId);
    }

    public void delByMenuId(long menuId) {
        sysRoleMenuMapper.delByMenuId(menuId);
    }

    public Boolean addRoleMenu(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.addSysRoleMenu(sysRoleMenu) == 1;
    }

}
