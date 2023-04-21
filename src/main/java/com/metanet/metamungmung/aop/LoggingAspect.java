package com.metanet.metamungmung.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    Logger logger =  LoggerFactory.getLogger(LoggingAspect.class);

    //Service 패키지의 하위 패키지 + 모든 메서드에 적용
    @Around("execution(* com.metanet.metamungmung.service..*.*(..))")
    public Object logging(ProceedingJoinPoint pjp) throws Throwable {

        long startAt = System.currentTimeMillis();
        logger.info("-----------> REQUEST : {}({})",
                pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName());
        Object result = pjp.proceed();
        long endAt = System.currentTimeMillis();
        logger.info("-----------> RESPONSE : {}({}) = {} ({}ms)",
                pjp.getSignature().getDeclaringTypeName(), pjp.getSignature().getName(), result, endAt - startAt);

        return result;
    }

}
