package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.vo.SysMenuVO;
import com.jpeony.lotus.core.pojo.domain.SysRoleDO;
import com.jpeony.lotus.core.pojo.dto.SysMenuDelDTO;
import com.jpeony.lotus.core.pojo.dto.SysMenuDTO;

import java.util.List;

public interface SysMenuService {

    List<SysMenuVO> allPermissionRoutes(String token) throws Exception;

    List<SysMenuVO> allRoutesByRole(SysRoleDO sysRoleDO);

    List<SysMenuVO> allRoutes(String token) throws Exception;

    Boolean addSysRoute(HeaderBO headerBO, SysMenuDTO sysMenuDTO) throws Exception;

    Boolean editSysRoute(HeaderBO headerBO, SysMenuDTO sysMenuDTO) throws Exception;

    Boolean delSysRoute(SysMenuDelDTO sysMenuDelDTO);
    
}
