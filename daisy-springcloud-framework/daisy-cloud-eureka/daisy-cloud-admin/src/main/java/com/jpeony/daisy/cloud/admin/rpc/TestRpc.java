package com.jpeony.daisy.cloud.admin.rpc;

import com.jpeony.daisy.cloud.common.intercepter.FeignIntercepter;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "course", configuration = FeignIntercepter.class)
public interface TestRpc {
    @GetMapping("/test/testRpc/test")
    String test();
}
