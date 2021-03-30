package com.jpeony.shopping.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class NavListResponse extends AbstractResponse {

    private List<PanelContentDto> pannelContentDtos;
}
