package com.lanhuigu.common.pojo.bo;

import lombok.Data;

/**
 * 业务处理对象，根据DO转换为自己想要的BO，基于BO进行业务处理
 *
 * @author yihonglei
 * @date 2019/10/12 3:19 PM
 */
@Data
public class TestBO {
    private Integer id;
    private String testName;
}
