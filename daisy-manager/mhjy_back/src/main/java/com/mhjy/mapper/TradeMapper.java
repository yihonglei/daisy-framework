package com.mhjy.mapper;

import com.mhjy.entity.Trade;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TradeMapper {
    List<Trade> tradehistory(long uid);

    List<Trade> sysTradeList(long cid, int type, int status, String startDate, String endDate);

    int trade(Trade trade);

    Trade tradeWithTradeNo(String tradeno);

    int updateStatus(String tradeno, int status);

    /* 累计充值金额 */
    String totalChargeMoney(Date date, int index, int type);
}
