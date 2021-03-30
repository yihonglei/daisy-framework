package com.jpeony.user;

import com.jpeony.user.dto.KaptchaCodeRequest;
import com.jpeony.user.dto.KaptchaCodeResponse;

public interface IKaptchaService {

    /**
     * 获取图形验证码
     */
    KaptchaCodeResponse getKaptchaCode(KaptchaCodeRequest request);

    /**
     * 验证图形验证码
     */
    KaptchaCodeResponse validateKaptchaCode(KaptchaCodeRequest request);

}
