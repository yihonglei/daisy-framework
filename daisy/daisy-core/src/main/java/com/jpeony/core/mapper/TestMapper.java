package com.jpeony.core.mapper;

import com.jpeony.core.pojo.domain.TestDO;
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
     * <p>
     * 通过@DataSource(value = DataSourceType.SLAVE)，指定使用从库数据源
     */
//    @DataSource(value = DataSourceType.SLAVE)
    @Select("select * from test where id = #{id}")
    TestDO queryTestById(@Param("id") int id);

    /**
     * MyBatis XML方式
     */
    TestDO queryTestByIdXml(@Param("id") int id);
}
