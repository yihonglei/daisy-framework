package com.jpeony.commons.datasource.aop;

import com.jpeony.commons.datasource.annotation.UseMaster;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 强制走主库注解
 *
 * @author yihonglei
 */
@Slf4j
@Component
@Aspect
public class DataSourceAop {

    @Pointcut("execution(* com.jpeony..*.mapper..*.*(..))")
    public void dsPointCut() {
    }

    @Around("dsPointCut()")
    public Object master(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object ret = null;

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        UseMaster useMaster = method.getAnnotation(UseMaster.class);

        HintManager hintManager = null;
        try {
            if (Objects.nonNull(useMaster)) {
                HintManager.clear();
                hintManager = HintManager.getInstance();
                hintManager.setMasterRouteOnly();
            }
            ret = joinPoint.proceed(args);
        } catch (Exception ex) {
            log.error("exception error", ex);
        } catch (Throwable ex2) {
            log.error("Throwable", ex2);
        } finally {
            if (Objects.nonNull(useMaster) && Objects.nonNull(hintManager)) {
                hintManager.close();
            }
        }
        return ret;
    }
}