package com.spring.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
@EnableAspectJAutoProxy
public class CRMLoggingAspect {

    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    //setup pointcuts
    @Pointcut("execution(* com.spring.demo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.spring.demo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.spring.demo.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forDaoPackage() || forServicePackage()")
    private void forAppFlow() {
    }

    //add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {
        Object method = joinPoint.getSignature().toShortString();
        logger.info("In @Before advice, calling method: " + method);

        //get the args and display them
        Object[] args = joinPoint.getArgs();
        for (Object tempArgs : args) {
            logger.info("argument: " + tempArgs);
        }
    }

    //add @After advice
    @AfterReturning(
            pointcut="forAppFlow()",
            returning="theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        // display method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("=====>> in @AfterReturning: from method: " + theMethod);

        // display data returned
        logger.info("=====>> result: " + theResult);

    }
}
