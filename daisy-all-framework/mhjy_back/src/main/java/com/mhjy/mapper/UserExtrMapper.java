package com.mhjy.mapper;

import com.mhjy.entity.UserExtr;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface UserExtrMapper {
    //获取userExtr信息
    UserExtr getUserExtr(long uid);
    UserExtr getUserExtrWithAttCode(String attCode);

    int addUserExtr(UserExtr userExtr);

    //更新用户Cid
    int updateUserExtrCid(long cid, long uid, Date updatedAt);
    //更新用户状态
    int updateUserExtrEnable(int enable, long uid, Date updatedAt);

    //更新用户状态
    int updateUserExtrMatch(int ismatch, long uid, Date match_expire, Date updatedAt);

    //更新用户状态
    int updateUserExtrVIP(int isVIP, long uid, Date vip_expire, Date updatedAt);

    int updateUserExtrIncome(double income, long uid, Date updatedAt);

    int updateUserExtrRemain(double remain, long uid, Date updatedAt);

}
