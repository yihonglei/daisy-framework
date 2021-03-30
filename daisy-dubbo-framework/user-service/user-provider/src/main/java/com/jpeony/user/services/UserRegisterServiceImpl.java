package com.jpeony.user.services;

import com.jpeony.commons.tool.exception.ValidateException;
import com.jpeony.user.IUserRegisterService;
import com.jpeony.user.constants.SysRetCodeConstants;
import com.jpeony.user.dal.entitys.Member;
import com.jpeony.user.dal.entitys.UserVerify;
import com.jpeony.user.dal.persistence.MemberMapper;
import com.jpeony.user.dal.persistence.UserVerifyMapper;
import com.jpeony.user.dto.UserRegisterRequest;
import com.jpeony.user.dto.UserRegisterResponse;
import com.jpeony.user.registerVerification.KafKaRegisterSuccProducer;
import com.jpeony.user.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

@Slf4j
@Service
public class UserRegisterServiceImpl implements IUserRegisterService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    RedissonClient redissonClient;

    @Autowired
    KafKaRegisterSuccProducer kafKaRegisterSuccProducer;

    @Autowired
    UserVerifyMapper userVerifyMapper;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        log.info("Begin UserLoginServiceImpl.register: request:" + request);
        UserRegisterResponse response = new UserRegisterResponse();
        try {
            validUserRegisterRequest(request);
            Member member = new Member();
            member.setUsername(request.getUserName());
            member.setPassword(DigestUtils.md5DigestAsHex(request.getUserPwd().getBytes()));

            member.setState(1);
            member.setCreated(new Date());
            member.setUpdated(new Date());
            member.setIsVerified("N");//为激活
            member.setEmail(request.getEmail());
            if (memberMapper.insert(member) != 1) {
                response.setCode(SysRetCodeConstants.USER_REGISTER_FAILED.getCode());
                response.setMsg(SysRetCodeConstants.USER_REGISTER_FAILED.getMessage());
                return response;
            }
            //插入用户验证表
            UserVerify userVerify = new UserVerify();
            userVerify.setUsername(member.getUsername());
            String key = member.getUsername()+member.getPassword()+UUID.randomUUID().toString();
            userVerify.setUuid(DigestUtils.md5DigestAsHex(key.getBytes()));
            userVerify.setIsExpire("N");//注册信息是否过期
            userVerify.setIsVerify("N");//是否验证成功
            userVerify.setRegisterDate(new Date());
            if(userVerifyMapper.insert(userVerify)!=1){
                response.setCode(SysRetCodeConstants.USER_REGISTER_VERIFY_FAILED.getCode());
                response.setMsg(SysRetCodeConstants.USER_REGISTER_VERIFY_FAILED.getMessage());
                return response;
            }
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            //发送消息到KafKa 目前由于发送邮件激活
            Map map = new HashMap();
            map.put("username",userVerify.getUsername());
            map.put("key",userVerify.getUuid());
            map.put("email",member.getEmail());
            kafKaRegisterSuccProducer.sendRegisterSuccInfo(map);
        } catch (Exception e) {
            log.error("UserLoginServiceImpl.register Occur Exception :" + e);
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        return response;
    }

    //校验参数以及校验用户名是否存在
    private void validUserRegisterRequest(UserRegisterRequest request) {
        request.requestCheck();
        Example example = new Example(Member.class);
        example.createCriteria().andEqualTo("state", 1).andEqualTo("username", request.getUserName());

        List<Member> users = memberMapper.selectByExample(example);
        if (users != null && users.size() > 0) {
            throw new ValidateException(SysRetCodeConstants.USERNAME_ALREADY_EXISTS.getCode(), SysRetCodeConstants.USERNAME_ALREADY_EXISTS.getMessage());
        }
    }
}
