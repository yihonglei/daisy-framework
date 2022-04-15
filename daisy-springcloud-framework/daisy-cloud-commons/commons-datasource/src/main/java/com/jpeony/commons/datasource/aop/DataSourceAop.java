package com.jpeony.commons.datasource.aop;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.jpeony.commons.datasource.annotation.DB;
import com.jpeony.commons.datasource.annotation.UseMaster;
import com.jpeony.commons.datasource.exception.DBException;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author yihonglei
 */
@Aspect
@Order(-10) // 在 Spring 事务生成代理对象之前指定数据源
@Component
public class DataSourceAop {
    private static final String MASTER = "-master";
    private static final String SLAVE = "-slave";

    //    @Pointcut("execution(* com.jpeony..*.mapper.*.*(..))")
    public void dsPointCut() {
    }

    //    @Before("dsPointCut()")
    public void process(JoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> clazz = point.getTarget().getClass();
        String dbName = getDBName(clazz);
        String datasource = dbName + MASTER;
        boolean useMaster = getUseMaster(method);
        if (!useMaster) {
            datasource = dbName + SLAVE;
        }
        DynamicDataSourceContextHolder.push(datasource);
    }

    private String getDBName(Class<?> targetClass) {
        DB dbName = AnnotationUtils.findAnnotation(targetClass, DB.class);
        if (dbName == null) {
            throw new DBException(targetClass.getName() + ", no specified database");
        }
        return dbName.name();
    }

    private boolean getUseMaster(Method method) {
        UseMaster useMaster = method.getAnnotation(UseMaster.class);
        Update update = method.getAnnotation(Update.class);
        Insert insert = method.getAnnotation(Insert.class);
        Delete delete = method.getAnnotation(Delete.class);

        return (useMaster != null || update != null || insert != null || delete != null);
    }

    //    @After("dsPointCut()")
    public void afterAdvice() {
        DynamicDataSourceContextHolder.clear();
    }
}
