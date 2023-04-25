package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.bo.SysMenuBO;
import com.jpeony.lotus.core.pojo.domain.SysRole;
import com.jpeony.lotus.core.pojo.dto.SysMenuDelDto;
import com.jpeony.lotus.core.pojo.dto.SysMenuDto;

import java.util.List;

public interface SysMenuService {
    List<SysMenuBO> allPermissionRoutes(String token) throws Exception;

    List<SysMenuBO> allRoutesWithRole(SysRole sysRole);

    List<SysMenuBO> allRoutes(String token) throws Exception;

    Boolean addSysRoute(HeaderBO headerBO, SysMenuDto sysMenuDto) throws Exception;

    Boolean editSysRoute(HeaderBO headerBO, SysMenuDto sysMenuDto) throws Exception;

    Boolean delSysRoute(SysMenuDelDto sysMenuDelDto);
}
