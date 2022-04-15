package com.jpeony.boot.core.mapper;

import com.jpeony.boot.common.annotation.DB;
import com.jpeony.boot.common.annotation.UseMaster;
import com.jpeony.boot.common.constant.DBConstant;
import com.jpeony.boot.core.pojo.domain.TestDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Mapper 接口
 *
 * @author yihonglei
 */
@DB(name = DBConstant.JPEONY)
public interface TestMapper {

    /**
     * MyBatis 注解形式
     */
    @Select("select * from test where id = #{id}")
    TestDO queryTestById(@Param("id") int id);

    @UseMaster
    @Select("select * from test where id = #{id}")
    TestDO queryTestByIdMaster(@Param("id") int id);

    @Update("update test set test_name = #{testName} where id = #{id}")
    int updateTestById(@Param("id") int id, @Param("testName") String testName);

    /**
     * MyBatis XML方式
     */
    TestDO queryTestByIdXml(@Param("id") int id);
}
