package com.jpeony.shopping.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProductCateDto implements Serializable {
    private Long id;
    private String name;
    private Long parentId;
    private Boolean isParent;
    private String iconUrl;
}
