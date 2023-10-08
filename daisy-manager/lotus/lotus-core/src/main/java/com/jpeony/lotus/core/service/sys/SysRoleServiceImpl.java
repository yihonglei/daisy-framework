package com.jpeony.lotus.core.service.sys;

import com.jpeony.lotus.common.enums.ErrorCodeEnum;
import com.jpeony.lotus.common.exception.BizException;
import com.jpeony.lotus.core.mapper.SysRoleMapper;
import com.jpeony.lotus.core.pojo.vo.SysMenuVO;
import com.jpeony.lotus.core.pojo.vo.SysRoleVO;
import com.jpeony.lotus.core.pojo.vo.SysUserVO;
import com.jpeony.lotus.core.pojo.domain.SysRoleDO;
import com.jpeony.lotus.core.pojo.domain.SysRoleMenuDO;
import com.jpeony.lotus.core.pojo.dto.SysRoleDTO;
import com.jpeony.lotus.core.service.SysMenuService;
import com.jpeony.lotus.core.service.SysRoleMenuService;
import com.jpeony.lotus.core.service.SysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    SysRoleMapper sysRoleMapper;

    @Autowired
    SysMenuService sysMenuService;

    @Autowired
    SysUserServiceImpl sysUserService;

    @Autowired
    SysRoleMenuService sysRoleMenuService;

    @Override
    public SysRoleDO roleById(long roleId) {

        return sysRoleMapper.roleById(roleId);
    }

    @Override
    public List<SysRoleDO> allRolesIgnore() {

        return sysRoleMapper.allRoles();
    }

    /**
     * 权限管理获取所有的角色及角色权限信息
     */
    @Override
    public List<SysRoleVO> allRoles() {
        List<SysRoleDO> roleList = sysRoleMapper.allRoles();

        List<SysRoleVO> resultList = new ArrayList<>();
        for (SysRoleDO sysRoleDO : roleList) {
            SysRoleVO sysRoleVO = new SysRoleVO();
            sysRoleVO.setId(sysRoleDO.getId());
            sysRoleVO.setName(sysRoleDO.getName());
            sysRoleVO.setIntro(sysRoleDO.getIntro());

            List<SysMenuVO> list = sysMenuService.allRoutesByRole(sysRoleDO);
            sysRoleVO.setRoutes(list);
            resultList.add(sysRoleVO);
        }

        return resultList;
    }


    /**
     * 权限管理 新增角色
     */
    @Override
    public SysRoleVO addRole(String token, SysRoleDTO sysRoleDto) throws Exception {
        long createdBy = 1;
        SysRoleVO sysRoleVO = new SysRoleVO();

        // 校验角色名称是否存在
        SysRoleDO sysRoleDO0 = sysRoleMapper.roleByRoleName(sysRoleDto.getName());
        if (sysRoleDO0 != null) {
            throw new BizException(ErrorCodeEnum.SYS_ADD_ROLE_EXIST.getCode(), ErrorCodeEnum.SYS_ADD_ROLE_EXIST.getMsg());
        }

        // 获取角色创建者
        SysUserVO sysUserVO = sysUserService.userInfo(token);
        createdBy = sysUserVO.getId();

        // 获取橘色序号
        Date now = new Date();
        List<SysRoleDO> sysRoleDOList = sysRoleMapper.allRoles();
        int sort = 0;
        for (SysRoleDO sysRoleDO : sysRoleDOList) {
            int tmpSort = sysRoleDO.getSort();
            if (tmpSort > sort) {
                sort = tmpSort;
            }
        }

        SysRoleDO sysRoleDO = new SysRoleDO();
        sysRoleDO.setName(sysRoleDto.getName());
        sysRoleDO.setIntro(sysRoleDto.getIntro());
        sysRoleDO.setSort(sort + 1);
        sysRoleDO.setCreatedAt(now);
        sysRoleDO.setCreatedBy(createdBy);
        sysRoleDO.setUpdatedAt(now);
        sysRoleDO.setUpdatedBy(createdBy);

        boolean flag = sysRoleMapper.addSysRole(sysRoleDO) == 1;

        if (flag) {
            SysRoleDO sysRoleDO1 = sysRoleMapper.roleByRoleName(sysRoleDto.getName());
            BeanUtils.copyProperties(sysRoleDO1, sysRoleVO);

            // 更新 rolemenu
            long roleId = sysRoleDO1.getId();
            String routeIds = sysRoleDto.getRouteIds();
            String[] menus = routeIds.split(",");
            for (String menu : menus) {
                long menuId = Long.valueOf(menu);
                SysRoleMenuDO sysRoleMenuDO = new SysRoleMenuDO();
                sysRoleMenuDO.setRoleId(roleId);
                sysRoleMenuDO.setMenuId(menuId);
                sysRoleMenuDO.setCreatedAt(now);
                sysRoleMenuDO.setCreatedBy(createdBy);
                sysRoleMenuDO.setUpdatedAt(now);
                sysRoleMenuDO.setUpdatedBy(createdBy);
                sysRoleMenuService.addRoleMenu(sysRoleMenuDO);
            }

            List<SysMenuVO> sysMenuVOList = sysMenuService.allRoutesByRole(sysRoleDO1);
            sysRoleVO.setRoutes(sysMenuVOList);
        }

        return sysRoleVO;
    }

    /**
     * 权限管理 编辑角色
     */
    @Override
    public SysRoleVO editRole(String token, SysRoleDTO sysRoleDTO) throws Exception {
        long updatedBy = 1;
        SysRoleVO sysRoleVO = new SysRoleVO();

        long roleId = sysRoleDTO.getId();
        String name = sysRoleDTO.getName();
        String intro = sysRoleDTO.getIntro();
        String routeIds = sysRoleDTO.getRouteIds();

        // 获取角色创建者
        SysUserVO sysUserVO = sysUserService.userInfo(token);
        updatedBy = sysUserVO.getId();

        // 删除 id 对应的 rolemenu 菜单
        sysRoleMenuService.delByRoleId(roleId);

        // 组装最新的模型
        Date now = new Date();
        // 获取橘色序号
        SysRoleDO sysRoleDO = sysRoleMapper.roleById(roleId);
        sysRoleDO.setName(name);
        sysRoleDO.setIntro(intro);
        sysRoleDO.setUpdatedAt(now);
        sysRoleDO.setUpdatedBy(updatedBy);

        boolean flag = sysRoleMapper.updateSysRole(sysRoleDO) == 1;

        if (flag) {
            SysRoleDO sysRoleDO1 = sysRoleMapper.roleByRoleName(sysRoleDTO.getName());
            BeanUtils.copyProperties(sysRoleDO1, sysRoleVO);

            // 更新 rolemenu
            long roleid = sysRoleDO1.getId();
            String[] menus = routeIds.split(",");
            for (String menu : menus) {
                long menuId = Long.valueOf(menu);
                SysRoleMenuDO sysRoleMenuDO = new SysRoleMenuDO();
                sysRoleMenuDO.setRoleId(roleid);
                sysRoleMenuDO.setMenuId(menuId);
                sysRoleMenuDO.setCreatedAt(now);
                sysRoleMenuDO.setCreatedBy(updatedBy);
                sysRoleMenuDO.setUpdatedAt(now);
                sysRoleMenuDO.setUpdatedBy(updatedBy);
                sysRoleMenuService.addRoleMenu(sysRoleMenuDO);
            }

            List<SysMenuVO> sysMenuVOList = sysMenuService.allRoutesByRole(sysRoleDO1);
            sysRoleVO.setRoutes(sysMenuVOList);
        }

        return sysRoleVO;
    }

    /**
     * 权限管理 删除角色
     */
    @Override
    public Boolean delRole(String token, SysRoleDTO sysRoleDTO) throws Exception {
        long roleId = sysRoleDTO.getId();

        // 删除 id 对应的 rolemenu 菜单
        sysRoleMenuService.delByRoleId(roleId);

        return sysRoleMapper.delByRoleId(roleId) == 1;
    }

}
