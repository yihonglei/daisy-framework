package com.jpeony.user.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.util.List;

@Data
public class AddressListResponse extends AbstractResponse {

    private List<AddressDto> addressDtos;
}
