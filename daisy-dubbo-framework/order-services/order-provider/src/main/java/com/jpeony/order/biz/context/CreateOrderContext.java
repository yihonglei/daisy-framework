package com.jpeony.order.biz.context;/**
 * Created by mic on 2019/8/2.
 */

import com.jpeony.order.dto.CartProductDto;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CreateOrderContext extends AbsTransHandlerContext{

    private Long userId;

    private Long addressId;

    private String tel;

    private String userName;

    private String streetName;

    private BigDecimal orderTotal;

    List<CartProductDto> cartProductDtoList;

    private List<Long> buyProductIds;

    private String buyerNickName;

    private String uniqueKey; //业务唯一id

}
