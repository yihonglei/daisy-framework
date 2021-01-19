package com.jpeony.daisy.cloud.admin.dao;

import com.jpeony.daisy.cloud.admin.domain.RoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 角色
 */
@Mapper
public interface RoleDao {

    RoleDO get(Long roleId);

    List<RoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(RoleDO role);

    int update(RoleDO role);

    int remove(Long roleId);

    int batchRemove(Long[] roleIds);

    List<Long> roleIdsByUserId(Long userId);
}
