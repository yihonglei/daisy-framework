package com.jpeony.user.converter;

import com.jpeony.user.dal.entitys.Member;
import com.jpeony.user.dto.QueryMemberResponse;
import com.jpeony.user.dto.UpdateMemberRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MemberConverter {

    @Mappings({})
    QueryMemberResponse member2Res(Member member);

    @Mappings({})
    Member updateReq2Member(UpdateMemberRequest request);
}
