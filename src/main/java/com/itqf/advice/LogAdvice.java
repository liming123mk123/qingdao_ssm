package com.itqf.advice;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * author: 赵伟风
 * date: 2019/6/20 15:36
 * description:增强类
 */

@Aspect   //切面： 注解的形式切点和增强都在一个类里
@Component
public class LogAdvice {

    /**
     *
     *  输出时间和方法的信息 到文件中
     *  ‘
     *  发送邮件
     */

    Logger logger = Logger.getLogger(LogAdvice.class);

    @Pointcut("execution(* com..service.*Impl.*(..))")
    public void pc(){}


    @Before("execution(* com..service.*Impl.*(..))")
    public void beforeLog(JoinPoint joinPoint){

        String info = getInfo(joinPoint);

        logger.warn("方法进入：--》"+info);

    }




    @After("LogAdvice.pc()")
    public void afterLog(JoinPoint joinPoint){

        String info = getInfo(joinPoint);

        logger.warn("方法结束：--》"+info);

    }


    @AfterThrowing(pointcut = "LogAdvice.pc()",throwing = "throwable")
    public void afterThrowingLog(JoinPoint joinPoint,Throwable throwable){


        String message = throwable.getMessage();

        String info = getInfo(joinPoint);

        logger.error("报错了 起来debug:"+info+"-->"+message);

    }


    public String getInfo(JoinPoint joinPoint){

        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        long time = System.currentTimeMillis();

        String info = "类名："+className + " -- 方法名："+methodName + " --时间："+time;

        return info;
    }


}
