package com.jpeony.coupon.strategy;

import com.jpeony.coupon.dto.UseCouponRequest;

public abstract class SaleStrategy {
    public abstract boolean match(UseCouponRequest request);

    public abstract void perform();
}
