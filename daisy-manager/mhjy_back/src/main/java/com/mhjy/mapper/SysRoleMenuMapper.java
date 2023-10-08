package com.mhjy.mapper;

import com.mhjy.entity.SysRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysRoleMenuMapper {

    List rolesWithMenuId(long menuId);

    void delByRoleId(long roleId);

    void delByMenuId(long menuId);

    int addSysRoleMenu(SysRoleMenu sysRoleMenu);
}
