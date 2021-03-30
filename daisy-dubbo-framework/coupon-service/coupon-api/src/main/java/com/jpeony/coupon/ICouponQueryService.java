package com.jpeony.coupon;

import java.util.List;

public interface ICouponQueryService {
    // 查询所有优惠券
    void queryAll();

    // 根据userId查询所有优惠券
    void queryAllByUserId();

    // 根据userId查询所有可用优惠券
    void queryActiveByUserId();

    // 检查创建订单时传递的优惠券id是否有效
    void checkCouponIsValid(Integer... ids);

    // 统计查询
}
