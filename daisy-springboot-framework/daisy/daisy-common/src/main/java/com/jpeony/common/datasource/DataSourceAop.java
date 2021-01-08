package com.jpeony.common.datasource;

import com.jpeony.common.annotation.DB;
import com.jpeony.common.annotation.UseMaster;
import com.jpeony.common.enums.DataSourceTypeEnum;
import com.jpeony.common.enums.ErrorCodeEnum;
import com.jpeony.common.exception.DBException;
import com.jpeony.common.utils.MatchUtils;
import org.apache.commons.collections.CollectionUtils;
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
import java.util.ArrayList;
import java.util.Random;

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
        if (StringUtils.isBlank(targetDataSource)) {
            throw new DBException(ErrorCodeEnum.DATA_SOURCE_ERROR);
        }

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

        DataSourceTypeEnum[] values = DataSourceTypeEnum.values();
        ArrayList<String> slaves = new ArrayList<>(DataSourceTypeEnum.values().length);
        for (DataSourceTypeEnum dataSourceType : values) {
            if (useMaster) {
                boolean matchMaster = MatchUtils.matchDataSource(dbName.toUpperCase() + MatchUtils.PATTERN_MATCH_MASTER, dataSourceType.name());
                if (!matchMaster) {
                    continue;
                }
                return dataSourceType.name();
            } else {
                boolean matchSlave = MatchUtils.matchDataSource(dbName.toUpperCase() + MatchUtils.PATTERN_MATCH_SLAVE, dataSourceType.name());
                if (matchSlave) {
                    slaves.add(dataSourceType.name());
                }
            }
        }

        return randomSlave(slaves);
    }

    public String getDBName(ProceedingJoinPoint point) {
        Class<?> targetClass = point.getTarget().getClass();
        DB dbName = AnnotationUtils.findAnnotation(targetClass, DB.class);
        if (dbName == null) {
            throw new DBException(targetClass.getName() + "未指定数据库");
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

    private String randomSlave(ArrayList<String> slaves) {
        if (CollectionUtils.isEmpty(slaves)) {
            return null;
        }
        Random random = new Random();
        return slaves.get(random.nextInt(slaves.size()));
    }
}
