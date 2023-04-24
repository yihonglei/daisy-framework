package com.mhjy.constant;

/**
 * @author yangnan
 */
public final class InterceptorConstant {
    public final static String NGINX = "/nginx.html";
    public final static String ADD_COUPON = "/coupon/add";
    public final static String UPDATE_COUPON = "/coupon/updateStatus";
    public final static String EXPENSE = "/v1/trip/expense";
    public final static String MONGO = "/mongo/**";
    public final static String V1_ADD_COUPON = "/v1/passenger/coupon/addCoupon";
    public final static String DH_ADD_COUPON = "/v1/passenger/coupon/addCouponDaHui";
    public final static String FAW_APPLY_SUBMIT = "/v1/passenger/faw/apply/submit";
    public final static String CENTURY_EXPORT = "/v1/passenger/century/export";
    public final static String CENTURY_VIPLIST = "/v1/passenger/century/vipList";
    public final static String PLACE_THE_ORDER = "/order/businessOrder/**";
    public final static String COUPON_REASSIGNMENT = "/v1/passenger/coupon/reassignment";
    public final static String CHECK = "/v1/passenger/secretary/check";
    public final static String TOKEN_DEL = "/token/del";
    public final static String CUSTOMER_CANCEL = "/v1/passenger/cancel/customer";
    public final static String THIRD_CANCEL = "/v1/passenger/cancel/third";
    public final static String APPLET = "/v1/applet/**";
    public final static String CANCEL_REASON = "/v1/passenger/cancel/reason";
    public final static String TRIP_SHARE = "/v1/passenger/share/queryData";
    public final static String OPEN_CITY = "/v1/passenger/city/openCity";

    public final static String H5_PASSENGER_LOGIN = "/v5/passenger/login/**";


    public final static String V1_ADD_COUPON_FAW = "/v1/passenger/coupon/addCouponFaw";

    public final static String ADD_DEVICE_INFO = "/v1/passenger/push/addDeviceInfo";

    public final static String MINI = "/sp/**";

    public final static String GETCODE = "/identify/**";

    /**日志*/
    public final static String LOGGER = "/logger/**";
    /**
     * 预估详情
     */
    public final static String ESTIMATED_DETAIL = "/passenger/estimated/detail";

    public final static String PHONE_REDIS = "/v1/passenger/group/phone/redis";

    public final static String ACTIVIT_DATE = "/v5/passenger/share/activityDate";

    public final static String MIGRATE = "/v1/passenger/safe/migrate";

    public final static String DELETE_TOKEN = "/v1/passenger/redis/deleteToken";

    public final static String DELETE_LOCK = "/v1/passenger/redis/deleteLock";

    public final static String ACTIVE_WX = "/v5/passenger/active/wx/**";

    public final static String TOEKN_ADD = "/token/add";

    public final static String PASSENGER_DESTINATION = "/v1/passenger/destination/update";

    public final static String LOGIN_LOGIN_RELEVANT = "/passenger/identify/**";

    public final static String GET_ALL_CITYS = "/v1/passenger/city/getAllCityS";

    public final static String PASSENGER_LBS_REVERSE = "/v1/passenger/lbs/reverse";

    public final static String HOTEL_H5_PAY = "/v1/passenger/payment/hotel/payH5";

    public final static String HOTEL_H5_INVOICE = "/v1/identify/invoice/**";

    public final static String PASSENGER_ASSISTANT_INPUTTIPS = "/v5/passenger/home/assistant/inputtips";

    public final static String HOTEL_ORDER_PAY_DETAIL = "/v1/passenger/payment/hotel/orderDetail";

    /**header拦截器不需要拦截的请求*/
    public final static String[] EXCLUDE_HEADER = {TRIP_SHARE};
    /**token拦截器不需要拦截的请求*/
    public final static String[] EXCLUDE_TOKEN = {HOTEL_ORDER_PAY_DETAIL,PASSENGER_ASSISTANT_INPUTTIPS,HOTEL_H5_INVOICE,HOTEL_H5_PAY,PASSENGER_LBS_REVERSE,GET_ALL_CITYS,LOGIN_LOGIN_RELEVANT,PASSENGER_DESTINATION,TOEKN_ADD, ACTIVE_WX, DELETE_TOKEN, DELETE_LOCK, MIGRATE, ACTIVIT_DATE, PHONE_REDIS,H5_PASSENGER_LOGIN, OPEN_CITY,ADD_DEVICE_INFO,V1_ADD_COUPON_FAW,DH_ADD_COUPON,CHECK,COUPON_REASSIGNMENT,CENTURY_VIPLIST,CENTURY_EXPORT,NGINX,ADD_COUPON,V1_ADD_COUPON,UPDATE_COUPON,EXPENSE,MONGO,FAW_APPLY_SUBMIT,LOGGER,TOKEN_DEL,CUSTOMER_CANCEL,APPLET,MINI,GETCODE,CANCEL_REASON,TRIP_SHARE,THIRD_CANCEL};
    /**header拦截器不需要拦截的请求*/
    public final static String[] INCLUDE_HEADER = {PLACE_THE_ORDER};

}
