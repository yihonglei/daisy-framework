package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.domain.SysRoleMenu;

import java.util.List;

public interface SysRoleMenuService {

    List<SysRoleMenu> rolesWithMenuId(long menuId);

    void delByRoleId(long roleId);

    void delByMenuId(long menuId);

    Boolean addRoleMenu(SysRoleMenu sysRoleMenu);

}
