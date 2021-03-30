package com.jpeony.user.bootstrap;

import com.jpeony.user.dal.entitys.User;
import com.jpeony.user.registerVerification.KafKaRegisterSuccProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafaSendTest {
    @Autowired
    KafKaRegisterSuccProducer kafKaRegisterSuccProducer;

    @Test
    public void sendMesg(){
        User user = new User();
        user.setUsername("test");
        user.setAddress("北京");
        user.setEmail("sssss@163.com");
        kafKaRegisterSuccProducer.sendRegisterSuccInfo(null);
    }

}
