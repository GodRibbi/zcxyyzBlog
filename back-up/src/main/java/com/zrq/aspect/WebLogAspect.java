package com.zrq.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Aspect
@Component
@Log4j2
public class WebLogAspect {
    private Object runAndSaveLog(ProceedingJoinPoint pjp) {
        long beginTime = System.currentTimeMillis();
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable e) {
        } finally {
        }
        long endTime = System.currentTimeMillis();
        log.info("time:" + (endTime - beginTime));
        return result;
    }

    @Around("execution(* com.zrq.controller..*.*(..))")
    Object aroundRestcontroller(ProceedingJoinPoint pjp) {
        return runAndSaveLog(pjp);
    }

}
