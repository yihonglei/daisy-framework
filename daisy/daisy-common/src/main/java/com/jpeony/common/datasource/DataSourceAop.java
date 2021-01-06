package com.jpeony.common.datasource;

import com.jpeony.common.annotation.DB;
import com.jpeony.common.annotation.UseMaster;
import com.jpeony.common.constant.DBConstant;
import com.jpeony.common.enums.DataSourceType;
import com.jpeony.common.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Update;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源处理
 *
 * @author yihonglei
 */
@Aspect
@Order(1)
@Component
public class DataSourceAop {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.jpeony.core..*.*(..))")
    public void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        String targetDataSource = getTargetDataSource(point);
        MultipleDataSourceContextHolder.setDataSourceType(targetDataSource);

        try {
            return point.proceed();
        } finally {
            MultipleDataSourceContextHolder.clearDataSourceType();
        }
    }

    public String getTargetDataSource(ProceedingJoinPoint point) {
        String dbName = getDBName(point);
        boolean useMaster = getUseMaster(point);

        String targetDataSource = StringUtils.EMPTY;
        switch (dbName) {
            case DBConstant.JPEONY:
                if (useMaster) {
                    targetDataSource = DataSourceType.JPEONY_MASTER.name();
                } else {
                    // TODO Optimize 可以配置多个从库，通过随机等负载方式，选取一个从库
                    targetDataSource = DataSourceType.JPEONY_SLAVE.name();
                }
                break;
            case DBConstant.USER:
                if (useMaster) {
                    targetDataSource = DataSourceType.USER_MASTER.name();
                } else {
                    // TODO Optimize 可以配置多个从库，通过随机等负载方式，选取一个从库
                    targetDataSource = DataSourceType.USER_SLAVE.name();
                }
                break;
            default:
                break;
        }
        if (StringUtils.isBlank(targetDataSource)) {
            throw new BizException("数据源获取异常");
        }
        return targetDataSource;
    }

    public String getDBName(ProceedingJoinPoint point) {
        Class<?> targetClass = point.getTarget().getClass();
        DB dbName = AnnotationUtils.findAnnotation(targetClass, DB.class);
        if (dbName == null) {
            // TODO 抛异常
        }
        return dbName.name();
    }

    public boolean getUseMaster(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        UseMaster useMaster = method.getAnnotation(UseMaster.class);
        Update update = method.getAnnotation(Update.class);
        Insert insert = method.getAnnotation(Insert.class);
        Delete delete = method.getAnnotation(Delete.class);

        if (useMaster != null || update != null || insert != null || delete != null) {
            return true;
        }
        return false;
    }
}
