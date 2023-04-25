package com.jpeony.lotus.test.mapper;

import com.jpeony.lotus.core.mapper.SysUserRoleMapper;
import com.jpeony.lotus.core.pojo.domain.SysUserRole;
import com.jpeony.lotus.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Mapper测试
 *
 * @author yihonglei
 */
@Slf4j
public class MapperTest extends BaseServletTest {
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Test
    public void testAnnotation() {
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.userRoleByUserId(1);
        log.info("sysUserRoles={}", sysUserRoles);
    }
}
