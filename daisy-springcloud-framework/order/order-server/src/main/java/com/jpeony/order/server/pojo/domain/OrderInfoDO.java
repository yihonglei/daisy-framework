package com.jpeony.order.server.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author yihonglei
 */
@Data
public class OrderInfoDO {
    @TableId(type = IdType.AUTO)
    private Integer orderId;
    private String orderName;
    private Integer status;
}
