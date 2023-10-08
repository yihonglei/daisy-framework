package com.mhjy.mapper;

import com.mhjy.entity.SysCashOutFee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysCashOutFeeMapper {
    // 新增用户的fee率
    int addCashOutFee(SysCashOutFee sysCashOutFee);

    SysCashOutFee cashOutFee(int type, long uid);

    //@Param(value="type")
    List<SysCashOutFee> sysFeeList(int type, String username);

    int sysUpdateFee(long id, String fee);

    int sysDeleteFee(long id);
}
