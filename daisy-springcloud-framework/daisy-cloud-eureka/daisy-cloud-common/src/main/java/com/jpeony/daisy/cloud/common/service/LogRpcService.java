package com.jpeony.daisy.cloud.common.service;

import com.jpeony.daisy.cloud.common.dto.LogDO;
import com.jpeony.daisy.cloud.common.intercepter.FeignIntercepter;
import com.jpeony.daisy.cloud.common.utils.R;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;

@Headers("Content-Type:application/json")
@FeignClient(name = "api-base", configuration = FeignIntercepter.class)
public interface LogRpcService {
    @Async
    @PostMapping("log/save")
    R save(LogDO logDO);
}
