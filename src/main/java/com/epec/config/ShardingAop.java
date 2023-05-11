package com.epec.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.infra.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Description
 * @Author zhaoxianxing
 * @Date 2023/5/11 16:06
 */
@Slf4j
@Component
@Aspect
public class ShardingAop {

    @Pointcut("@annotation(com.epec.config.ShardingJdbcMaster)")
    public void shardingJdbcMasterPointcut() {
    }

    @Around("shardingJdbcMasterPointcut()")
    public Object shardingJdbcMasterHold(final ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Object ret = null;
        log.info(joinPoint.toShortString());

        HintManager hintManager = null;
        try {
            HintManager.clear();
            hintManager = HintManager.getInstance();
            hintManager.setWriteRouteOnly();
            ret = joinPoint.proceed(args);
        }catch (Exception ex){
            log.error("exception error",ex);
        }catch (Throwable ex2){
            log.error("Throwable",ex2);
        }finally {
            hintManager.close();
        }
        return ret;
    }
}
