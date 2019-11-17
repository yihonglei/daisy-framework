package com.lanhuigu.core.chain.risk.model;

import lombok.Data;

/**
 * 过滤需要的资源
 *
 * @author yihonglei
 * @date 2019/10/31 4:49 PM
 */
@Data
public class Accessory<T> {
    /**
     * 上下文
     */
    private String orderNO;
}
