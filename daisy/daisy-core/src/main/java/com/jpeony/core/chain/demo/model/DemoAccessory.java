package com.jpeony.core.chain.demo.model;

import com.jpeony.core.pojo.vo.RiskVO;
import lombok.Data;

/**
 * 过滤需要的资源
 *
 * @author yihonglei
 */
@Data
public class DemoAccessory<T> {
    /**
     * 请求上下文
     */
    private DemoContext demoContext;
    /**
     * 请求返回结果
     */
    private Object result;
}
