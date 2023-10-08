package com.mhjy.mapper;

import com.mhjy.entity.CashOut;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CashOutMapper {

    int cashout(CashOut cashOut);

    List<CashOut> cashOutHistory(long uid);

    List<CashOut> sysCashoutList(long cid, String alipaynum, String realname, int status);

    int verifyCashout(long id, int status, String reason, Date updated_at);

    /* 累计提现金额 */
    String totalOutMoney(Date date, int type);
    /* 提现&待转账次数 */
    String totalTransCount(Date date, int type, int status);

    int verifyOutMoney(long id, Date updated_at, int status);
}
