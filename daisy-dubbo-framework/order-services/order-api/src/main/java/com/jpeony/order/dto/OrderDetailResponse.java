package com.jpeony.order.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDetailResponse extends AbstractResponse{

    private String orderId;

    private BigDecimal payment;

    private Integer paymentType;

    private BigDecimal postFee;

    private Integer status;

    private Date createTime;

    private Date updateTime;

    private Date paymentTime;

    private Date consignTime;

    private Date endTime;

    private Date closeTime;

    private String shippingName;

    private String shippingCode;

    private Long userId;

    private String buyerMessage;

    private String buyerNick;

    private Integer buyerComment;

    private List<OrderItemDto> orderItemDto;

    private OrderShippingDto orderShippingDto;
}
