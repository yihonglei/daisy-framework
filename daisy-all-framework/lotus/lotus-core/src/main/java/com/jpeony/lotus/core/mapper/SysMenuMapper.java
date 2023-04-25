package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysMenu;

import java.util.List;

public interface SysMenuMapper {
    List<SysMenu> routesWithParentId(long parentId);

    SysMenu routeWithId(long id);

    int addSysRoute(SysMenu sysMenu);

    int editSysRoute(SysMenu sysMenu);

    int delSysRouteByMenuId(long menuId);
}
