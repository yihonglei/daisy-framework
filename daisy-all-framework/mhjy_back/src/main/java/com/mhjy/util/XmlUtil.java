package com.mhjy.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * xml处理工具类
 */
public class XmlUtil {
    /*
     * xml转map
     */
    public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        HashMap<String, String> map = new HashMap<String,String>();
        SAXReader reader = new SAXReader();

        InputStream ins = request.getInputStream();
        Document doc = reader.read(ins);

        Element root = doc.getRootElement();
        @SuppressWarnings("unchecked")
        List<Element> list = (List<Element>)root.elements();

        for(Element e:list){
            map.put(e.getName(), e.getText());
        }
        ins.close();
        return map;
    }
}

