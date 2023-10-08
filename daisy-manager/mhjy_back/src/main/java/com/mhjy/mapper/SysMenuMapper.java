package com.mhjy.mapper;

import com.mhjy.entity.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuMapper {
    List<SysMenu> routesWithParentId(long parentId);

    SysMenu routeWithId(long id);

    int addSysRoute(SysMenu sysMenu);

    int editSysRoute(SysMenu sysMenu);

    int delSysRouteByMenuId(long menuId);
}
