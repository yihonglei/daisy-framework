package com.mhjy.mapper;

import com.mhjy.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    User getUserWithOpenId(String openId);
    User getUserInfoWithUid(long uid);
    int addUser(User user);
}
