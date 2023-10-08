package com.jpeony.lotus.core.service;

import com.jpeony.lotus.core.pojo.vo.SysRoleVO;
import com.jpeony.lotus.core.pojo.domain.SysRoleDO;
import com.jpeony.lotus.core.pojo.dto.SysRoleDTO;

import java.util.List;

public interface SysRoleService {

    SysRoleDO roleById(long roleId);

    List<SysRoleDO> allRolesIgnore();

    List<SysRoleVO> allRoles();

    SysRoleVO addRole(String token, SysRoleDTO sysRoleDTO) throws Exception;

    SysRoleVO editRole(String token, SysRoleDTO sysRoleDTO) throws Exception;

    Boolean delRole(String token, SysRoleDTO sysRoleDTO) throws Exception;

}
