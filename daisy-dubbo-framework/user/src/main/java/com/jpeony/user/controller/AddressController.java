package com.jpeony.user.controller;

import com.jpeony.commons.core.ResponseData;
import com.jpeony.commons.core.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    /**
     * 获取地址列表信息
     */
    @GetMapping("/address")
    public ResponseData address() {
        return new ResponseUtil<>().setData(null);
    }


}
