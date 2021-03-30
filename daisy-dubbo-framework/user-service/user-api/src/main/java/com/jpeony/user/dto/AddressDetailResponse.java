package com.jpeony.user.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

@Data
public class AddressDetailResponse extends AbstractResponse {
	private AddressDto addressDto;
    
}
