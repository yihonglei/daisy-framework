package com.jpeony.order.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

@Data
public class OrderCountResponse extends AbstractResponse{

    private int count;
}
