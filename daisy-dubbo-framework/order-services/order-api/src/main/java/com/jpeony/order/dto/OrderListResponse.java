package com.jpeony.order.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class OrderListResponse extends AbstractResponse{

    private List<OrderDetailInfo> detailInfoList;

    /**
     * 总记录数
     */
    private Long total;

}
