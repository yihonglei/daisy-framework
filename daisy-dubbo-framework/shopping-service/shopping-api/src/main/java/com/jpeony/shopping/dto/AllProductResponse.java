package com.jpeony.shopping.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class AllProductResponse extends AbstractResponse {

    private List<ProductDto> productDtoList;

    private Long total;
}
