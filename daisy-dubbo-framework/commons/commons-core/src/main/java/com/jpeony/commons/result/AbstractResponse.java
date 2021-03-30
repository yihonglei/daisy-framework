package com.jpeony.commons.result;

import java.io.Serializable;

public abstract class AbstractResponse implements Serializable {

    private static final long serialVersionUID = 7505997295595095971L;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
