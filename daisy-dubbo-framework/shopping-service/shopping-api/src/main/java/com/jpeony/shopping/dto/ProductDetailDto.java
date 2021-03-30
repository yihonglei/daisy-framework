package com.jpeony.shopping.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductDetailDto implements Serializable {

    private static final long serialVersionUID = -597050593951733519L;
    private Long productId;

    private BigDecimal salePrice;

    private String productName;

    private String subTitle;

    private Long limitNum;

    private String productImageBig;

    private String detail;

    private List<String> productImageSmall;
}
