package com.jpeony.dubbo.user.provider.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yihonglei
 */
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = -3168536066977915639L;

    private Integer id;
    private String userName;
    private Integer sex;
}
