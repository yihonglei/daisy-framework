package com.jpeony.daisy.cloud.admin.service;

import com.jpeony.daisy.cloud.admin.domain.RoleDO;
import com.jpeony.daisy.cloud.admin.dto.UserRoleDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface RoleService {

    RoleDO get(Long id);

    List<RoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RoleDO role);

    int update(RoleDO role);

    int remove(Long id);

    List<UserRoleDTO> list(Long userId);

    int batchremove(Long[] ids);

    /**
     * 获取用户的角色id
     */
    List<Long> RoleIdsByUserId(Long userId);
}
