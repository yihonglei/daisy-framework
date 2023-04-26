package com.jpeony.lotus.core.service.sys;

import com.jpeony.lotus.common.enums.ErrorCodeEnum;
import com.jpeony.lotus.common.exception.BizException;
import com.jpeony.lotus.core.mapper.SysMenuMapper;
import com.jpeony.lotus.core.pojo.bo.HeaderBO;
import com.jpeony.lotus.core.pojo.vo.SysMenuVO;
import com.jpeony.lotus.core.pojo.vo.SysUserVO;
import com.jpeony.lotus.core.pojo.domain.SysMenuDO;
import com.jpeony.lotus.core.pojo.domain.SysRoleDO;
import com.jpeony.lotus.core.pojo.domain.SysRoleMenuDO;
import com.jpeony.lotus.core.pojo.domain.SysUserRoleDO;
import com.jpeony.lotus.core.pojo.dto.SysMenuDelDTO;
import com.jpeony.lotus.core.pojo.dto.SysMenuDTO;
import com.jpeony.lotus.core.service.SysMenuService;
import com.jpeony.lotus.core.service.SysUserRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysUserServiceImpl sysUserService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleMenuServiceImpl sysRoleMenuService;

    @Autowired
    SysRoleServiceImpl sysRoleService;

    /**
     * 登录时获取用户所有权限菜单
     */
    @Override
    public List<SysMenuVO> allPermissionRoutes(String token) throws Exception {
        // 获取当前用户的角色
        SysUserVO sysUserVO = sysUserService.userInfo(token);
        long userId = sysUserVO.getId();

        // 获取当前用户角色 一个用户有多个角色
        List<SysUserRoleDO> sysUserRoleDOList = sysUserRoleService.userRolesByUserId(userId);

        if (sysUserRoleDOList == null) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_USER_NOROLE.getCode(), ErrorCodeEnum.SYS_LOGIN_USER_NOROLE.getMsg());
        }

        return filterAsyncRoutes(0, userId, sysUserRoleDOList);
    }

    private List<SysMenuVO> filterAsyncRoutes(long parentId, long userId, List<SysUserRoleDO> sysUserRoleDOList) {
        List<SysMenuVO> resultList = new ArrayList<>();
        // 获取所有菜单 带权限
        List<SysMenuDO> sysMenuDOList = sysMenuMapper.routesByParentId(parentId);
        for (int i = 0; i < sysMenuDOList.size(); i++) {
            SysMenuVO sysMenuVO = new SysMenuVO();

            SysMenuDO sysMenuDO = sysMenuDOList.get(i);
            BeanUtils.copyProperties(sysMenuDO, sysMenuVO);

            long menuId = sysMenuDO.getId();
            List<SysRoleMenuDO> roleMenuList = sysRoleMenuService.rolesByMenuId(menuId);

            Boolean hasPermission = false;
            List<String> roles = new ArrayList<>();
            if (userId == 1) {
                hasPermission = true;
                List<SysRoleDO> sysRoleDOList = sysRoleService.allRolesIgnore();
                for (SysRoleDO sysRoleDO1 : sysRoleDOList) {
                    roles.add(sysRoleDO1.getName());
                }
            } else {
                // 根据 menuid 获取对应的权限 roles
                if (roleMenuList != null) {
                    for (SysRoleMenuDO sysRoleMenuDO : roleMenuList) {
                        long roleid = sysRoleMenuDO.getRoleId();
                        SysRoleDO sysRoleDO = sysRoleService.roleById(roleid);
                        for (SysUserRoleDO sysUserRoleDO : sysUserRoleDOList) {
                            if (roleid == sysUserRoleDO.getRoleId()) {
                                hasPermission = true;
                                roles.add(sysRoleDO.getName());
                            }
                        }
                    }
                }
            }

            if (hasPermission) {
                sysMenuVO.setRoles(roles);
                List<SysMenuVO> children = filterAsyncRoutes(menuId, userId, sysUserRoleDOList);
                if (children != null && children.size() != 0) {
                    sysMenuVO.setChildren(children);
                } else {
                    sysMenuVO.setChildren(new ArrayList<>());
                }

                resultList.add(sysMenuVO);
            }
        }
        return resultList;
    }

    /**
     * 权限管理页面 - 编辑 - 根据角色获取角色对应的权限列表
     */
    @Override
    public List<SysMenuVO> allRoutesByRole(SysRoleDO sysRoleDO) {

        return filterAsyncRoutesByRole(0, sysRoleDO);
    }

    private List<SysMenuVO> filterAsyncRoutesByRole(long parentId, SysRoleDO sysRoleDO) {
        List<SysMenuVO> resultList = new ArrayList<>();
        // 获取所有菜单 带权限
        List<SysMenuDO> sysMenuDOList = sysMenuMapper.routesByParentId(parentId);
        for (int i = 0; i < sysMenuDOList.size(); i++) {
            SysMenuVO sysMenuVO = new SysMenuVO();

            SysMenuDO sysMenuDO = sysMenuDOList.get(i);
            BeanUtils.copyProperties(sysMenuDO, sysMenuVO);

            long menuId = sysMenuDO.getId();
            List<SysRoleMenuDO> roleMenuList = sysRoleMenuService.rolesByMenuId(menuId);

            Boolean hasPermission = false;
            List<String> roles = new ArrayList<>();
            if (sysRoleDO.getName().equalsIgnoreCase("admin")) {
                hasPermission = true;
                List<SysRoleDO> sysRoleDOList = sysRoleService.allRolesIgnore();
                for (SysRoleDO sysRoleDO1 : sysRoleDOList) {
                    roles.add(sysRoleDO1.getName());
                }
                sysMenuVO.setChecked(1);
            } else {
                // 根据 menuid 获取对应的权限 roles
                if (roleMenuList != null) {
                    for (SysRoleMenuDO sysRoleMenuDO : roleMenuList) {
                        long roleId = sysRoleMenuDO.getRoleId();
                        SysRoleDO msysRoleDO = sysRoleService.roleById(roleId);
                        if (roleId == sysRoleDO.getId()) {
                            hasPermission = true;
                            roles.add(msysRoleDO.getName());
                        }
                    }
                }
            }

            if (hasPermission) {
                sysMenuVO.setRoles(roles);
                List<SysMenuVO> children = filterAsyncRoutesByRole(menuId, sysRoleDO);
                if (children != null && children.size() != 0) {
                    sysMenuVO.setChildren(children);
                } else {
                    sysMenuVO.setChildren(new ArrayList<>());
                }

                resultList.add(sysMenuVO);
            }
        }
        return resultList;
    }

    /**
     * 权限管理页面获取 tree 数据，所有权限列表
     */
    @Override
    public List<SysMenuVO> allRoutes(String token) throws Exception {

        return allSysRoutes(0);
    }

    private List<SysMenuVO> allSysRoutes(long parentId) {
        List<SysMenuVO> resultList = new ArrayList<>();
        // 获取所有菜单 带权限
        List<SysMenuDO> sysMenuDOList = sysMenuMapper.routesByParentId(parentId);
        for (int i = 0; i < sysMenuDOList.size(); i++) {
            SysMenuVO sysMenuVO = new SysMenuVO();

            SysMenuDO sysMenuDO = sysMenuDOList.get(i);
            BeanUtils.copyProperties(sysMenuDO, sysMenuVO);

            long menuId = sysMenuDO.getId();
            List<SysMenuVO> children = allSysRoutes(menuId);
            if (children != null && children.size() != 0) {
                sysMenuVO.setChildren(children);
            } else {
                sysMenuVO.setChildren(new ArrayList<>());
            }

            resultList.add(sysMenuVO);
        }
        return resultList;
    }

    @Override
    public Boolean addSysRoute(HeaderBO headerBO, SysMenuDTO sysMenuDTO) throws Exception {
        String token = headerBO.getToken();
        SysUserVO sysUserVO = sysUserService.userInfo(token);
        Date now = new Date();

        SysMenuDO sysMenuDO = new SysMenuDO();
        BeanUtils.copyProperties(sysMenuDTO, sysMenuDO);
        sysMenuDO.setParentId(sysMenuDTO.getParentId());
        sysMenuDO.setCreatedAt(now);
        sysMenuDO.setCreatedBy(sysUserVO.getId());
        sysMenuDO.setUpdatedAt(now);
        sysMenuDO.setUpdatedBy(sysUserVO.getId());

        return sysMenuMapper.addSysRoute(sysMenuDO) == 1;
    }

    @Override
    public Boolean editSysRoute(HeaderBO headerBO, SysMenuDTO sysMenuDTO) throws Exception {
        String token = headerBO.getToken();
        SysUserVO sysUserVO = sysUserService.userInfo(token);
        Date now = new Date();

        long id = sysMenuDTO.getId();
        SysMenuDO sysMenuDO0 = sysMenuMapper.routeById(id);
        if (sysMenuDO0 == null) {
            throw new BizException(ErrorCodeEnum.SYS_EDIT_ROUTE_NOT_EXIST.getCode(), ErrorCodeEnum.SYS_EDIT_ROUTE_NOT_EXIST.getMsg());
        }

        SysMenuDO sysMenuDO = new SysMenuDO();
        BeanUtils.copyProperties(sysMenuDTO, sysMenuDO);
        sysMenuDO.setUpdatedAt(now);
        sysMenuDO.setUpdatedBy(sysUserVO.getId());

        return sysMenuMapper.editSysRoute(sysMenuDO) == 1;
    }

    @Override
    public Boolean delSysRoute(SysMenuDelDTO sysMenuDelDTO) {
        String ids = sysMenuDelDTO.getIds();
        String[] idsArr = ids.split(",");
        for (String id : idsArr) {
            long menuId = Long.valueOf(id);
            sysMenuMapper.delSysRouteByMenuId(menuId);

            sysRoleMenuService.delByMenuId(menuId);
        }
        return true;
    }

}
