package com.jpeony.user.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {

    private Long addressId;

    private Long userId;

    private String userName;

    private String tel;

    private String streetName;

    private Integer isDefault;
}
