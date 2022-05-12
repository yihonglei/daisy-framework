package com.jpeony.file.common.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class FileImportTest {

    private final static String FILE = "/Users/yihonglei/Downloads/phone.csv";

    private final static String UPDATE_SQL = "update car_fact_order set rider_name = '%s' where order_id = %d;";

    private final static String ORDER_FILE = "/Users/yihonglei/Downloads/order.csv";

    public static void main(String[] args) throws Exception {
        java.io.File f = new File(FILE);
        java.io.File f2 = new File(ORDER_FILE);
        BufferedReader reader = new BufferedReader(new FileReader(f));
        Map<String, String> map = new HashMap<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            String[] contents = line.split(",");
            map.put(contents[0], contents[1]);
        }
        reader.close();

        BufferedReader reader1 = new BufferedReader(new FileReader(f2));
        while ((line = reader1.readLine()) != null) {
            String[] contents = line.split(",");
            String name = map.get(contents[1]);
            if (name == null || name.isEmpty()) {
                continue;
            }
            System.out.println(String.format(UPDATE_SQL, name, Integer.parseInt(contents[0])));
        }
        reader.close();
    }
}
