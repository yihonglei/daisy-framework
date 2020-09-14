package com.jpeony.core.mapper;

import com.jpeony.common.pojo.domain.SchemaTablesDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yihonglei
 */
public interface InformationSchemaMapper {
    @Select("select TABLE_SCHEMA, TABLE_NAME from information_schema.TABLES where TABLE_NAME like '%2020%'")
    List<SchemaTablesDO> queryTables();

    @Select("select TABLE_NAME from information_schema.TABLES where TABLE_NAME like '%${tableTmp}%' order by CREATE_TIME desc limit 1")
    String getTable(@Param("tableTmp") String tableTmp);
}
