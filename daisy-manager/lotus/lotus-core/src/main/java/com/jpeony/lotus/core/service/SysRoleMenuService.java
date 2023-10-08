package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.domain.SysRoleMenuDO;

import java.util.List;

public interface SysRoleMenuService {

    List<SysRoleMenuDO> rolesByMenuId(long menuId);

    void delByRoleId(long roleId);

    void delByMenuId(long menuId);

    Boolean addRoleMenu(SysRoleMenuDO sysRoleMenuDO);

}
