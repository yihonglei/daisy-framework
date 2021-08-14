package com.jpeony.feign.user.api.response;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class UserInfoDTO {
    private Integer userId;
    private String userName;
    private Integer age;
}
