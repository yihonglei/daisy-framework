package com.jpeony.feign.user.api.request;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class UserInfoParam {
    private String userName;
    private Integer age;
}
