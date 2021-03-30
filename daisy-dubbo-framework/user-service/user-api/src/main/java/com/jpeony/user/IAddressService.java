package com.jpeony.user;

import com.jpeony.user.dto.*;
import com.jpeony.user.dto.*;

/**
 * 快递邮寄地址相关服务
 */
public interface IAddressService {

    /**
     * 获取地址列表，根据用户id
     */
    AddressListResponse addressList(AddressListRequest request);

    /**
     * 获取地址详细，根据地址id
     */
    AddressDetailResponse addressDetail(AddressDetailRequest request);

    /**
     * 添加地址
     */
    AddAddressResponse createAddress(AddAddressRequest request);

    /**
     * 修改地址信息
     */
    UpdateAddressResponse updateAddress(UpdateAddressRequest request);

    /**
     * 删除地址（for id）
     */
    DeleteAddressResponse deleteAddress(DeleteAddressRequest request);
}
