package com.lanhuigu.core.mapper;

import com.lanhuigu.common.pojo.domain.TestDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Mapper接口
 *
 * @author yihonglei
 * @date 2019/11/16 2:02 PM
 */
public interface TestMapper {
    /**
     * MyBatis注解方式
     *
     * @author yihonglei
     * @date 2019/11/16 11:10 AM
     */
    @Select("select * from test where id = #{id}")
    TestDO queryTestById(@Param("id") int id);

    /**
     * MyBatis XML方式
     *
     * @author yihonglei
     * @date 2019/11/16 11:10 AM
     */
    TestDO queryTestByIdXml(@Param("id") int id);
}
