package com.mhjy.pojo.Dto;

import lombok.Data;

@Data
public class GoodsListDto {
    /**
     * 1 VIP商品  2 续费商品
     */
    private int type;
}
