package com.mhjy.pojo.Dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class LoginDto {
    @NonNull
    private String code;

    private String attCode;
}
