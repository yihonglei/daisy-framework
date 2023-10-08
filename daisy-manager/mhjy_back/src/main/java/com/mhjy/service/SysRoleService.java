package com.mhjy.service;

import com.mhjy.entity.SysRole;
import com.mhjy.entity.SysRoleMenu;
import com.mhjy.entity.SysUser;
import com.mhjy.enums.ErrorCodeEnum;
import com.mhjy.exception.BizException;
import com.mhjy.mapper.SysRoleMapper;
import com.mhjy.pojo.Bo.SysMenuBO;
import com.mhjy.pojo.Bo.SysRoleBO;
import com.mhjy.pojo.Bo.SysUserBO;
import com.mhjy.pojo.Dto.SysRoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    public SysRole roleWithId(long roleId) {
        return sysRoleMapper.roleWithId(roleId);
    }

    public List<SysRole> allRolesIgnore() {
        List<SysRole> roleList = sysRoleMapper.allRoles();

        return roleList;
    }

    /**
     * 权限管理获取所有的角色及角色权限信息
     *
     * @return
     */
    public List<SysRoleBO> allRoles() {
        List<SysRole> roleList = sysRoleMapper.allRoles();

        List<SysRoleBO> resultList = new ArrayList<>();
        for (SysRole sysRole : roleList) {
            SysRoleBO sysRoleBO = new SysRoleBO();
            sysRoleBO.setId(sysRole.getId());
            sysRoleBO.setName(sysRole.getName());
            sysRoleBO.setIntro(sysRole.getIntro());

            List<SysMenuBO> list = sysMenuService.allRoutesWithRole(sysRole);
            sysRoleBO.setRoutes(list);
            resultList.add(sysRoleBO);
        }

        return resultList;
    }


    /**
     * 权限管理 新增角色
     *
     * @return
     */
    public SysRoleBO addRole(String token, SysRoleDto sysRoleDto) throws Exception {
        long created_by = 1;
        SysRoleBO sysRoleBO = new SysRoleBO();

        // 校验角色名称是否存在
        SysRole sysRole0 = sysRoleMapper.roleWithRolename(sysRoleDto.getName());
        if (sysRole0 != null) {
            throw new BizException(ErrorCodeEnum.SYS_ADD_ROLE_EXIST.getCode(), ErrorCodeEnum.SYS_ADD_ROLE_EXIST.getMsg());
        }

        // 获取角色创建者
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        created_by = sysUserBO.getId();

        // 获取橘色序号
        Date now = new Date();
        List<SysRole> sysRoleList = sysRoleMapper.allRoles();
        int sort = 0;
        for (SysRole sysRole : sysRoleList) {
            int tmpSort = sysRole.getSort();
            if (tmpSort > sort) {
                sort = tmpSort;
            }
        }

        SysRole sysRole = new SysRole();
        sysRole.setName(sysRoleDto.getName());
        sysRole.setIntro(sysRoleDto.getIntro());
        sysRole.setSort(sort + 1);
        sysRole.setCreated_at(now);
        sysRole.setCreated_by(created_by);
        sysRole.setUpdated_at(now);
        sysRole.setUpdated_by(created_by);

        boolean flag = sysRoleMapper.addSysRole(sysRole) == 1;

        if (flag) {
            // 返回值
            SysRole sysRole1 = sysRoleMapper.roleWithRolename(sysRoleDto.getName());
            BeanUtils.copyProperties(sysRole1, sysRoleBO);

            // 更新rolemenu
            long roleid = sysRole1.getId();
            String routeIds = sysRoleDto.getRouteIds();
            String[] menus = routeIds.split(",");
            for (String menu:menus) {
                long menuId = Long.valueOf(menu);
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRole_id(roleid);
                sysRoleMenu.setMenu_id(menuId);
                sysRoleMenu.setCreated_at(now);
                sysRoleMenu.setCreated_by(created_by);
                sysRoleMenu.setUpdated_at(now);
                sysRoleMenu.setUpdated_by(created_by);
                sysRoleMenuService.addRoleMenu(sysRoleMenu);
            }

            List<SysMenuBO> sysMenuBOList = sysMenuService.allRoutesWithRole(sysRole1);
            sysRoleBO.setRoutes(sysMenuBOList);
        }

        return sysRoleBO;
    }

    /**
     * 权限管理 编辑角色
     *
     * @return
     */
    public SysRoleBO editRole(String token, SysRoleDto sysRoleDto) throws Exception {
        long updated_by = 1;
        SysRoleBO sysRoleBO = new SysRoleBO();

        long roleId = sysRoleDto.getId();
        String name = sysRoleDto.getName();
        String intro = sysRoleDto.getIntro();
        String routeIds = sysRoleDto.getRouteIds();

        // 获取角色创建者
        SysUserBO sysUserBO = sysUserService.userinfo(token);
        updated_by = sysUserBO.getId();

        // 删除id对应的rolemenu菜单
        sysRoleMenuService.delByRoleId(roleId);

        // 组装最新的模型
        Date now = new Date();
        // 获取橘色序号
        SysRole sysRole = sysRoleMapper.roleWithId(roleId);
        sysRole.setName(name);
        sysRole.setIntro(intro);
        sysRole.setUpdated_at(now);
        sysRole.setUpdated_by(updated_by);

        boolean flag = sysRoleMapper.updateSysRole(sysRole) == 1;

        if (flag) {
            // 返回值
            SysRole sysRole1 = sysRoleMapper.roleWithRolename(sysRoleDto.getName());
            BeanUtils.copyProperties(sysRole1, sysRoleBO);

            // 更新rolemenu
            long roleid = sysRole1.getId();
            String[] menus = routeIds.split(",");
            for (String menu:menus) {
                long menuId = Long.valueOf(menu);
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setRole_id(roleid);
                sysRoleMenu.setMenu_id(menuId);
                sysRoleMenu.setCreated_at(now);
                sysRoleMenu.setCreated_by(updated_by);
                sysRoleMenu.setUpdated_at(now);
                sysRoleMenu.setUpdated_by(updated_by);
                sysRoleMenuService.addRoleMenu(sysRoleMenu);
            }

            List<SysMenuBO> sysMenuBOList = sysMenuService.allRoutesWithRole(sysRole1);
            sysRoleBO.setRoutes(sysMenuBOList);
        }

        return sysRoleBO;
    }

    /**
     * 权限管理 删除角色
     *
     * @return
     */
    public Boolean delRole(String token, SysRoleDto sysRoleDto) throws Exception {
        long roleId = sysRoleDto.getId();

        // 删除id对应的rolemenu菜单
        sysRoleMenuService.delByRoleId(roleId);

        return sysRoleMapper.delByRoleId(roleId) == 1;
    }

}
