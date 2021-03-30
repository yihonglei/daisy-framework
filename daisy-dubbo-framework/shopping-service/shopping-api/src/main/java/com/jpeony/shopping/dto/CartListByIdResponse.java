package com.jpeony.shopping.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class CartListByIdResponse extends AbstractResponse {

    private List<CartProductDto> cartProductDtos;
}
