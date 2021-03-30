package com.jpeony.order.constants;

public class OrderConstants {

    public static int ORDER_STATUS_INIT = 0; //初始化状态
    public static int ORDER_STATUS_PAYED = 1; //已支付
    public static int ORDER_STATUS_TRANSACTION_SUCCESS = 4; //交易成功
    public static int ORDER_STATUS_TRANSACTION_CLOSE = 5; //交易关闭
    public static int ORDER_STATUS_TRANSACTION_FAILED = 6; //交易失败
    public static int ORDER_STATUS_TRANSACTION_CANCEL = 7; //订单取消
}
