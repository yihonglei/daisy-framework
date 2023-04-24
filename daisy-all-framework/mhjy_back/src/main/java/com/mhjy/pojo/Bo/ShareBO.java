package com.mhjy.pojo.Bo;

import lombok.Data;

@Data
public class ShareBO {

    private String url;

    private String jsapi_ticket;

    private String nonce_str;

    private String timestamp;

    private String signature;

    private String appId;

}
