package com.jpeony.shopping.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PanelDto implements Serializable {
    private static final long serialVersionUID = -9099372701554072936L;
    private Integer id;

    private String name;

    private Integer type;

    private Integer sortOrder;

    private Integer position;

    private Integer limitNum;

    private Integer status;

    private String remark;

    private List<PanelContentItemDto> panelContentItems;
}
