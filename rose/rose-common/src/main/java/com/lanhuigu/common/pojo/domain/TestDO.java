package com.lanhuigu.common.pojo.domain;

import lombok.Data;

/**
 * 与数据库对应，与数据库打交道的对象
 *
 * @author yihonglei
 */
@Data
public class TestDO {
    /**
     * 用户ID
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;
}
