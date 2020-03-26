package com.jpeony.core.mapper;

import com.jpeony.common.pojo.domain.TestDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Mapper接口
 *
 * @author yihonglei
 */
public interface TestMapper {
    /**
     * MyBatis注解方式
     *
     * @author yihonglei
     */
    @Select("select * from test where id = #{id}")
    TestDO queryTestById(@Param("id") int id);

    /**
     * MyBatis XML方式
     *
     * @author yihonglei
     */
    TestDO queryTestByIdXml(@Param("id") int id);
}
