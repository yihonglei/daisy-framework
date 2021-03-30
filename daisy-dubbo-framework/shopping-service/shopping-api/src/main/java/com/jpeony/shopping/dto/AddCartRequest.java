package com.jpeony.shopping.dto;

import com.jpeony.commons.core.AbstractRequest;
import lombok.Data;

@Data
public class AddCartRequest extends AbstractRequest{

    private Long userId;
    private Long itemId;
    private Integer num;

    @Override
    public void requestCheck() {

    }
}
