package com.jpeony.daisy.cloud.admin.service;

import com.jpeony.daisy.cloud.admin.domain.Tree;
import com.jpeony.daisy.cloud.admin.domain.MenuDO;
import com.jpeony.daisy.cloud.common.dto.RouterDTO;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public interface MenuService {
    Tree<MenuDO> getSysMenuTree(Long id);

    /**
     * 登录用户的权限
     */
    List<MenuDO> userMenus(Long userId);

    List<Tree<MenuDO>> listMenuTree(Long id);

    Tree<MenuDO> getTree();

    Tree<MenuDO> getTree(Long id);

    @CacheEvict(value = "permission", key = "#userId")
    boolean clearCache(Long userId);

    List<MenuDO> list(Map<String, Object> params);

    int remove(Long id);

    int save(MenuDO menu);

    int update(MenuDO menu);

    MenuDO get(Long id);

    Set<String> listPerms(Long userId);

    /**
     * 获取角色下的权限所有id
     *
     * @param roleId
     * @return
     */
    List<Long> MenuIdsByRoleId(Long roleId);

    /**
     * 用户的路由
     *
     * @return
     */
    List<RouterDTO> RouterDTOsByUserId(Long userId);

    /**
     * 用户权限
     */
    List<String> PermsByUserId(Long userId);
}
