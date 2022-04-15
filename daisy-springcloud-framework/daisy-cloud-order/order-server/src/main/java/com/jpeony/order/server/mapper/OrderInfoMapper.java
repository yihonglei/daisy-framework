package com.jpeony.order.server.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jpeony.order.server.pojo.domain.OrderInfoDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface OrderInfoMapper extends BaseMapper<OrderInfoDO> {
    /**
     * 单表从库查询返回 DO
     */
    @Select("select * from order_info where order_id = #{orderId}")
    OrderInfoDO queryOrderInfo(@Param("orderId") int orderId);

    /**
     * 单表主库查询返回 DO
     */
    @Select("select * from order_info where order_id = #{orderId}")
    OrderInfoDO queryOrderInfoMaster(@Param("orderId") int orderId);
}
