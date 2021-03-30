package com.jpeony.user.bootstrap;

import com.jpeony.user.dal.dao.AddressDao;
import com.jpeony.user.dal.entitys.Address;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressDaoTest {

    @Autowired
    AddressDao addressDao;

    @Test
    public void selectAllTest() {
        List<Address> addressList = addressDao.selectAll();
        addressList.forEach(address -> {
            System.out.println(address.getAddressId() + " | " + address.getTel());
        });
    }

    @Test
    public void insertTest() {

    }
}
