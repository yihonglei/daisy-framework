package com.mhjy.pojo.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class SysFinanceCashoutAnyDto {
    private Date date;
    private int type; // 1 天  2 月  3  年

}
