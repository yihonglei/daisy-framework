package com.jpeony.user.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class QueryMemberResponse extends AbstractResponse{

    private Long id;

    private String username;

    private String password;

    private String phone;

    private String email;

    private Date created;

    private Date updated;

    private String sex;

    private String address;

    private Integer state;

    private String file;

    private String description;

    private Integer points;

    private BigDecimal balance;
}
