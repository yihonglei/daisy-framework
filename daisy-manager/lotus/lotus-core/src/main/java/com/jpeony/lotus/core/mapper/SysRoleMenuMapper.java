package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysRoleMenuDO;

import java.util.List;

public interface SysRoleMenuMapper {

    List rolesByMenuId(long menuId);

    void delByRoleId(long roleId);

    void delByMenuId(long menuId);

    int addSysRoleMenu(SysRoleMenuDO sysRoleMenuDO);
    
}
