package com.jpeony.cloud.user.server.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class UserInfoDO {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private Integer age;
}
