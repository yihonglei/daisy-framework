package com.jpeony.order.dal.entitys;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderList {

    private String orderId;
    private Integer status;
    private Date createTime;
    private BigDecimal payment;
    private String itemId;
    private Integer num;
    private String title;
    private BigDecimal price;
    private BigDecimal totalFee;
    private String picPath;
}
