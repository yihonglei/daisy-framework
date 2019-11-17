package com.lanhuigu.core.chain.risk.model;

import com.lanhuigu.common.pojo.vo.RiskVO;
import lombok.Data;

/**
 * 返回结果
 *
 * @author yihonglei
 * @date 2019/10/31 4:51 PM
 */
@Data
public class RiskResult<T> {
    private RiskVO riskVO;

    public RiskResult() {
        riskVO = new RiskVO();
    }
}
