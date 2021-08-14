package com.jpeony.order.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jpeony.commons.datasource.annotation.DB;
import com.jpeony.commons.datasource.annotation.UseMaster;
import com.jpeony.order.constant.DBConstant;
import com.jpeony.order.pojo.domain.OrderInfoDO;
import com.jpeony.order.pojo.dto.OrderInfoDetailDTO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@DB(name = DBConstant.ORDER)
public interface OrderInfoMapper extends BaseMapper<OrderInfoDO> {

    /**
     * 单表从库查询返回 DO
     */
    @Select("select * from order_info where order_id = #{orderId}")
    OrderInfoDO queryOrderInfo(@Param("orderId") int orderId);

    /**
     * 单表主库查询返回 DO
     */
    @UseMaster
    @Select("select * from order_info where order_id = #{orderId}")
    OrderInfoDO queryOrderInfoMaster(@Param("orderId") int orderId);

    /**
     * 如果是多表关联查询返回 DTO
     */
    @UseMaster
    @Select("select * from order_info where order_name = #{orderName}")
    OrderInfoDetailDTO queryOrderInfoDetail(@Param("orderName") String orderName);

    /**
     * 更新返回
     */
    @Update("update order_info set user_name = #{userName} where order_id = #{orderId}")
    int updateOrderInfo(@Param("orderId") int orderId, @Param("orderName") String orderName);
}
