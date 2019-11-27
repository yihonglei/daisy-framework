package com.lanhuigu.core.chain.risk.model;

import com.lanhuigu.common.pojo.vo.RiskVO;
import lombok.Data;

/**
 * 过滤需要的资源
 *
 * @author yihonglei
 */
@Data
public class RiskAccessory<T> {
    /**
     * 请求上下文
     */
    private RiskContext riskContext;
    /**
     * 请求返回结果
     */
    private RiskVO riskVO;

    public RiskAccessory() {
        riskVO = new RiskVO();
    }
}
