package com.jpeony.lotus.core.service.sys;

import com.jpeony.lotus.core.mapper.SysRoleMenuMapper;
import com.jpeony.lotus.core.pojo.domain.SysRoleMenuDO;
import com.jpeony.lotus.core.service.SysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {

    @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;

    @Override
    public List<SysRoleMenuDO> rolesByMenuId(long menuId) {
        return sysRoleMenuMapper.rolesByMenuId(menuId);
    }

    @Override
    public void delByRoleId(long roleId) {
        sysRoleMenuMapper.delByRoleId(roleId);
    }

    @Override
    public void delByMenuId(long menuId) {
        sysRoleMenuMapper.delByMenuId(menuId);
    }

    @Override
    public Boolean addRoleMenu(SysRoleMenuDO sysRoleMenuDO) {
        return sysRoleMenuMapper.addSysRoleMenu(sysRoleMenuDO) == 1;
    }

}
