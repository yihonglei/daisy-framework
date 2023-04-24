package com.jpeony.boot.core.pojo.vo;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class RiskVO {
    /**
     * 用户类型(0，个人；1 企业；)
     */
    private Integer userType;
    /**
     * 用户风险标识(0，非风险用户；1，风险用户；)
     */
    private Integer riskFlag;

    public RiskVO() {
        userType = 0;
        riskFlag = 0;
    }
}
