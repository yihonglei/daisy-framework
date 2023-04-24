package com.mhjy.pojo.Bo;

import lombok.Data;

@Data
public class TradeBO {
    private String appId;
    private String signType;
    private String nonceStr;
    private String packages;
    private String timeStamp;
    private String paySign;
}
