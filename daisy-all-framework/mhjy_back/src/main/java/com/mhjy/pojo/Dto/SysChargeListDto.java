package com.mhjy.pojo.Dto;

import lombok.Data;

@Data
public class SysChargeListDto {

    private int status;

    //1 vip  2 代理续费 3 抽纸条 4 放纸条
    private int type;

    private String startDate;

    private String endDate;
}
