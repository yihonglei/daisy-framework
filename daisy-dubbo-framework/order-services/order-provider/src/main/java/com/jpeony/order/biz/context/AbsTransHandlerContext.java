package com.jpeony.order.biz.context;

import com.jpeony.order.biz.convert.TransConvert;
import lombok.Data;

/**
 * 交易相关的抽象
 */
@Data
public abstract class AbsTransHandlerContext implements TransHandlerContext {

    private String orderId;

    private TransConvert convert = null;


}
