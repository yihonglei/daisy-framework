package com.jpeony.shopping.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class NavListResponse extends AbstractResponse {

    private List<PanelContentDto> pannelContentDtos;
}
