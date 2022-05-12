package com.jpeony.file.common.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.jpeony.file.common.util.FileExporterUtil;

public class FileExportTest {

    private static String path = "/Users/yihonglei/Downloads";
    private static String name = "test";

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setCreateDate(new Date());
            person.setName("测试" + i);
            person.setPay(1.009);
            person.setPayAmount(new BigDecimal(1.0098));
            person.setPhone("123456789");
            personList.add(person);
        }
        FileExporterUtil.export(personList, path, name);
//        MessageFormat format = new MessageFormat("{0, number, #.##}");
//        System.out.println(format.format(new Object[]{0.009}));
    }
}
