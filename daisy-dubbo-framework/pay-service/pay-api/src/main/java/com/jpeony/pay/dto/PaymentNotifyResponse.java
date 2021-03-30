package com.jpeony.pay.dto;

import com.jpeony.commons.result.AbstractResponse;
import lombok.Data;

@Data
public class PaymentNotifyResponse extends AbstractResponse{
    /** 返回给服务端的执行结果的报文 */
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "PaymentNotifyResponse{" +
                "result='" + result + '\'' +
                "} " + super.toString();
    }
}
