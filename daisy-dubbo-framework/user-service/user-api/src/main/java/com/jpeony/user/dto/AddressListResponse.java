package com.jpeony.user.dto;

import com.jpeony.commons.core.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class AddressListResponse extends AbstractResponse {

    private List<AddressDto> addressDtos;
}
