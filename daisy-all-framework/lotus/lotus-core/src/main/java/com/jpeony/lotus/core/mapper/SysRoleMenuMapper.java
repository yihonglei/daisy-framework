package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuMapper {

    List rolesWithMenuId(long menuId);

    void delByRoleId(long roleId);

    void delByMenuId(long menuId);

    int addSysRoleMenu(SysRoleMenu sysRoleMenu);
}
