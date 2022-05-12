package com.jpeony.file.common.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yihonglei
 */
public class ClassUtil {

    /**
     * 获取带有注解的字段
     *
     * @param tClass
     * @param annotationClass
     * @return
     */
    public static List<Field> getAnnotationField(Class<?> tClass, Class<? extends Annotation> annotationClass) {
        List<Field> fields = new ArrayList<Field>();
        while (tClass != Object.class) {
            Field[] fs = tClass.getDeclaredFields();
            for (Field field : fs) {
                Annotation body = field.getAnnotation(annotationClass);
                if (body != null) {
                    fields.add(field);
                }
            }
            tClass = tClass.getSuperclass();
        }
        return fields;
    }
}
