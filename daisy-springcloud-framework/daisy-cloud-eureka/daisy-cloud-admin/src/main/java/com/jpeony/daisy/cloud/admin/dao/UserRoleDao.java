package com.jpeony.daisy.cloud.admin.dao;

import com.jpeony.daisy.cloud.admin.domain.UserRoleDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户与角色对应关系
 */
@Mapper
public interface UserRoleDao {

    UserRoleDO get(Long id);

    List<UserRoleDO> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(UserRoleDO userRole);

    int update(UserRoleDO userRole);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Long> listRoleId(Long userId);

    int removeByUserId(Long userId);

    int batchSave(List<UserRoleDO> list);

    int batchRemoveByUserId(Long[] ids);
}
