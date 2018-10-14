package com.wuhulala.spring.proxy.jdkspring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 功能说明: jdk测试切面<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
@Aspect
@Component
public class JdkAspect {

    @Pointcut(value = "execution(* com.wuhulala.spring.proxy.jdkspring..*(..))")
    public void log(){}
    ///////////////////////////// 方法区 ////////////////////////////////////


    @Before(value = "log()")
    public void logBefore() throws Throwable {
        System.out.println("before...");
    }
}
