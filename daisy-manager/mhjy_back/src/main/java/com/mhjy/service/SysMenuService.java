package com.mhjy.service;

import com.mhjy.entity.*;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.mapper.SysMenuMapper;
import com.mhjy.pojo.Bo.HeaderBO;
import com.mhjy.pojo.Bo.SysMenuBO;
import com.mhjy.pojo.Bo.SysUserBO;
import com.mhjy.pojo.Dto.SysMenuDelDto;
import com.mhjy.pojo.Dto.SysMenuDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuService {

    @Autowired
    SysMenuMapper sysMenuMapper;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysUserRoleService sysUserRoleService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Autowired
    SysRoleService sysRoleService;

    /**
     * 登录时获取用户所有权限菜单
     * @param token
     * @return
     */
    public List<SysMenuBO> allPermissionRoutes(String token) throws Exception {
        // 获取当前用户的角色
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        long uid = sysUserBO.getId();

        // 获取当前用户角色 一个用户有多个角色
        List<SysUserRole> sysUserRoleList = sysUserRoleService.userRolesWithUid(uid);

        if (sysUserRoleList == null) {
            throw new BizException(ErrorCodeEnum.SYS_LOGIN_USER_NOROLE.getCode(), ErrorCodeEnum.SYS_LOGIN_USER_NOROLE.getMsg());
        }

        List<SysMenuBO> resultList = filterAsyncRoutes(0, uid, sysUserRoleList);

        return resultList;
    }

    private List<SysMenuBO> filterAsyncRoutes(long parentId, long uid, List<SysUserRole> sysUserRoleList) {
        List<SysMenuBO> resultList = new ArrayList<>();
        // 获取所有菜单 带权限
        List<SysMenu> sysMenuList = sysMenuMapper.routesWithParentId(parentId);
        for(int i = 0; i < sysMenuList.size(); i++) {
            SysMenuBO sysMenuBO = new SysMenuBO();

            SysMenu sysMenu = sysMenuList.get(i);
            BeanUtils.copyProperties(sysMenu, sysMenuBO);

            long menuId = sysMenu.getId();
            List<SysRoleMenu> roleMenuList = sysRoleMenuService.rolesWithMenuId(menuId);

            Boolean hasPermission = false;
            List<String> roles = new ArrayList<>();
            if (uid == 1) {
                hasPermission = true;
                List<SysRole> sysRoleList = sysRoleService.allRolesIgnore();
                for (SysRole sysRole1: sysRoleList) {
                    roles.add(sysRole1.getName());
                }
            } else {
                // 根据menuid获取对应的权限roles
                if (roleMenuList != null) {
                    for (SysRoleMenu sysRoleMenu :roleMenuList) {
                        long roleid = sysRoleMenu.getRole_id();
                        SysRole sysRole = sysRoleService.roleWithId(roleid);
                        for (SysUserRole sysUserRole:sysUserRoleList) {
                            if (roleid == sysUserRole.getRole_id()) {
                                hasPermission = true;
                                roles.add(sysRole.getName());
                            }
                        }
                    }
                }
            }

            if (hasPermission) {
                sysMenuBO.setRoles(roles);
                List<SysMenuBO> children = filterAsyncRoutes(menuId, uid, sysUserRoleList);
                if (children != null && children.size() != 0) {
                    sysMenuBO.setChildren(children);
                } else {
                    sysMenuBO.setChildren(new ArrayList<>());
                }

                resultList.add(sysMenuBO);
            }
        }
        return resultList;
    }

    /**
     * 权限管理页面 - 编辑 - 根据角色获取角色对应的权限列表
     * @param sysRole
     * @return
     */
    public List<SysMenuBO> allRoutesWithRole(SysRole sysRole) {
        List<SysMenuBO> resultList = filterAsyncRoutesWithRole(0, sysRole);

        return resultList;
    }
    private List<SysMenuBO> filterAsyncRoutesWithRole(long parentId, SysRole sysRole) {
        List<SysMenuBO> resultList = new ArrayList<>();
        // 获取所有菜单 带权限
        List<SysMenu> sysMenuList = sysMenuMapper.routesWithParentId(parentId);
        for(int i = 0; i < sysMenuList.size(); i++) {
            SysMenuBO sysMenuBO = new SysMenuBO();

            SysMenu sysMenu = sysMenuList.get(i);
            BeanUtils.copyProperties(sysMenu, sysMenuBO);

            long menuId = sysMenu.getId();
            List<SysRoleMenu> roleMenuList = sysRoleMenuService.rolesWithMenuId(menuId);

            Boolean hasPermission = false;
            List<String> roles = new ArrayList<>();
            if (sysRole.getName().equalsIgnoreCase("admin")) {
                hasPermission = true;
                List<SysRole> sysRoleList = sysRoleService.allRolesIgnore();
                for (SysRole sysRole1: sysRoleList) {
                    roles.add(sysRole1.getName());
                }
                sysMenuBO.setChecked(1);
            } else {
                // 根据menuid获取对应的权限roles
                if (roleMenuList != null) {
                    for (SysRoleMenu sysRoleMenu :roleMenuList) {
                        long roleid = sysRoleMenu.getRole_id();
                        SysRole msysRole = sysRoleService.roleWithId(roleid);
                        if (roleid == sysRole.getId()) {
                            hasPermission = true;
                            roles.add(msysRole.getName());
                        }
                    }
                }
            }

            if (hasPermission) {
                sysMenuBO.setRoles(roles);
                List<SysMenuBO> children = filterAsyncRoutesWithRole(menuId, sysRole);
                if (children != null && children.size() != 0) {
                    sysMenuBO.setChildren(children);
                } else {
                    sysMenuBO.setChildren(new ArrayList<>());
                }

                resultList.add(sysMenuBO);
            }
        }
        return resultList;
    }

    /**
     * 权限管理页面获取tree数据，所有权限列表
     * @param token
     * @return
     * @throws Exception
     */
    public List<SysMenuBO> allRoutes(String token) throws Exception {
        List<SysMenuBO> resultList = allSysRoutes(0);

        return resultList;
    }

    private List<SysMenuBO> allSysRoutes(long parentId) {
        List<SysMenuBO> resultList = new ArrayList<>();
        // 获取所有菜单 带权限
        List<SysMenu> sysMenuList = sysMenuMapper.routesWithParentId(parentId);
        for(int i = 0; i < sysMenuList.size(); i++) {
            SysMenuBO sysMenuBO = new SysMenuBO();

            SysMenu sysMenu = sysMenuList.get(i);
            BeanUtils.copyProperties(sysMenu, sysMenuBO);

            long menuId = sysMenu.getId();
            List<SysMenuBO> children = allSysRoutes(menuId);
            if (children != null && children.size() != 0) {
                sysMenuBO.setChildren(children);
            } else {
                sysMenuBO.setChildren(new ArrayList<>());
            }

            resultList.add(sysMenuBO);
        }
        return resultList;
    }

    public Boolean addSysRoute(HeaderBO headerBO, SysMenuDto sysMenuDto) throws Exception {
        String token = headerBO.getToken();
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        Date now = new Date();

        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuDto, sysMenu);
        sysMenu.setParent_id(sysMenuDto.getParentid());
        sysMenu.setCreated_at(now);
        sysMenu.setCreated_by(sysUserBO.getId());
        sysMenu.setUpdated_at(now);
        sysMenu.setUpdated_by(sysUserBO.getId());

        return sysMenuMapper.addSysRoute(sysMenu) == 1;
    }

    public Boolean editSysRoute(HeaderBO headerBO, SysMenuDto sysMenuDto) throws Exception {
        String token = headerBO.getToken();
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        Date now = new Date();

        long id = sysMenuDto.getId();
        SysMenu sysMenu0 = sysMenuMapper.routeWithId(id);
        if (sysMenu0 == null) {
            throw new BizException(ErrorCodeEnum.SYS_EDIT_ROUTE_NOT_EXIST.getCode(), ErrorCodeEnum.SYS_EDIT_ROUTE_NOT_EXIST.getMsg());
        }

        SysMenu sysMenu = new SysMenu();
        BeanUtils.copyProperties(sysMenuDto, sysMenu);
        sysMenu.setUpdated_at(now);
        sysMenu.setUpdated_by(sysUserBO.getId());

        return sysMenuMapper.editSysRoute(sysMenu) == 1;
    }

    public Boolean delSysRoute(SysMenuDelDto sysMenuDelDto) {
        String ids = sysMenuDelDto.getIds();
        String[] idsArr = ids.split(",");
        for (String id:idsArr) {
            long menuId = Long.valueOf(id);
            sysMenuMapper.delSysRouteByMenuId(menuId);

            sysRoleMenuService.delByMenuId(menuId);
        }
        return true;
    }




}
