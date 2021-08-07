package com.jpeony.user.feign.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yihonglei
 */
@FeignClient(name = "daisy-cloud-user", fallback = UserApiFallBack.class)
public interface UserApi {
    @RequestMapping(value = "/user/getUser/{userId}", method = RequestMethod.GET)
    String getUser(@PathVariable("userId") int userId);
}
