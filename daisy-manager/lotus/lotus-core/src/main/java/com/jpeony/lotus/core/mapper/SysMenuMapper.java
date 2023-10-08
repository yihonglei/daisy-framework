package com.jpeony.lotus.core.mapper;

import com.jpeony.lotus.core.pojo.domain.SysMenuDO;

import java.util.List;

public interface SysMenuMapper {

    List<SysMenuDO> routesByParentId(long parentId);

    SysMenuDO routeById(long id);

    int addSysRoute(SysMenuDO sysMenuDO);

    int editSysRoute(SysMenuDO sysMenuDO);

    int delSysRouteByMenuId(long menuId);
    
}
