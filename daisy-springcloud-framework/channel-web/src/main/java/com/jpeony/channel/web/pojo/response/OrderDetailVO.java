package com.jpeony.channel.web.pojo.response;

import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderDetailVO {
    private String userName;
    private Integer age;
    private Integer orderId;
    private Integer status;
    private String orderName;
}