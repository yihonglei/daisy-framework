package com.jpeony.boot.core.pojo.bo;

import lombok.Data;

/**
 * 业务处理对象 BO，将 DO 或 DTO 转换为 BO ，基于 BO 进行业务操作
 *
 * @author yihonglei
 */
@Data
public class TestBO {
    private Integer id;
    private String testName;
}
