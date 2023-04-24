package com.mhjy.pojo.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class TradeDto {
    @NonNull
    private long gid;
    @NonNull
    private long uid;
    /**
     * 1 vip  2 代理续费 3 抽纸条 4 放纸条
     */
    @NonNull
    private int type;
}
