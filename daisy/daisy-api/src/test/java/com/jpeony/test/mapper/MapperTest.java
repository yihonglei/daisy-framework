package com.jpeony.test.mapper;

import com.jpeony.common.pojo.domain.SchemaTablesDO;
import com.jpeony.common.pojo.domain.TestDO;
import com.jpeony.core.mapper.InformationSchemaMapper;
import com.jpeony.core.mapper.TestMapper;
import com.jpeony.test.BaseServletTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mapper测试
 *
 * @author yihonglei
 */
@Slf4j
public class MapperTest extends BaseServletTest {
    @Autowired
    private TestMapper testMapper;
    @Autowired
    private InformationSchemaMapper schemaMapper;

    @Test
    public void testAnnotation() {
        TestDO testDO = testMapper.queryTestById(1);
        log.info("testDO annotation={}", testDO);
    }

    @Test
    public void testXml() {
        TestDO testDO = testMapper.queryTestByIdXml(1);
        log.info("testDO xml={}", testDO);
    }

    @Test
    public void queryTables() {
        List<SchemaTablesDO> tables = schemaMapper.queryTables();
        HashMap<String/*table*/, String/*schema*/> targetMap = new HashMap<>();
        for (SchemaTablesDO table : tables) {
            String[] arrs = table.getTableName().split("2020");
            String tableKey = arrs[0];
            targetMap.put(tableKey, table.getTableSchema());
        }

        HashMap<String/*schema*/, String/*table*/> finalMap = new HashMap<>();
        for (Map.Entry entry : targetMap.entrySet()) {
            String tableName = schemaMapper.getTable(entry.getKey().toString());
            System.out.println(entry.getValue() + "." + tableName);
        }
    }
}
