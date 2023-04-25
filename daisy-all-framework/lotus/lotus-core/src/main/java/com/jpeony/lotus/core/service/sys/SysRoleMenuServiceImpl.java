package com.jpeony.lotus.core.service.sys;

import com.jpeony.lotus.core.mapper.SysRoleMenuMapper;
import com.jpeony.lotus.core.pojo.domain.SysRoleMenu;
import com.jpeony.lotus.core.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenu> rolesWithMenuId(long menuId) {
        return sysRoleMenuMapper.rolesWithMenuId(menuId);
    }

    @Override
    public void delByRoleId(long roleId) {
        sysRoleMenuMapper.delByRoleId(roleId);
    }

    @Override
    public void delByMenuId(long menuId) {
        sysRoleMenuMapper.delByMenuId(menuId);
    }

    @Override
    public Boolean addRoleMenu(SysRoleMenu sysRoleMenu) {
        return sysRoleMenuMapper.addSysRoleMenu(sysRoleMenu) == 1;
    }

}
