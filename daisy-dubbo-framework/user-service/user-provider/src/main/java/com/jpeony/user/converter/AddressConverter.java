package com.jpeony.user.converter;

import com.jpeony.user.dal.entitys.Address;
import com.jpeony.user.dto.*;
import com.jpeony.user.dto.AddAddressRequest;
import com.jpeony.user.dto.AddressDto;
import com.jpeony.user.dto.UpdateAddressRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressConverter {

    @Mappings({})
    AddressDto address2List(Address addresses);

    /*@Mappings({})
    AddressDto address2Res(Address address);*/

    List<AddressDto> address2List(List<Address> addresses);

    @Mappings({})
    Address req2Address(AddAddressRequest request);

    @Mappings({})
    Address req2Address(UpdateAddressRequest request);
}
