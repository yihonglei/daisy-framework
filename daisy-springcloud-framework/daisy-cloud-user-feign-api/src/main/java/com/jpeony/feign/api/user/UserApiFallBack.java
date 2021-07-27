package com.jpeony.feign.api.user;

import org.springframework.stereotype.Component;

/**
 * User 用户，服务降级接口
 *
 * @author yihonglei
 */
@Component
public class UserApiFallBack implements UserApi {
    @Override
    public String getUser(int userId) {
        return "用户信息接口降级";
    }
}