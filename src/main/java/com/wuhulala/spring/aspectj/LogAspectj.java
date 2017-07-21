package com.wuhulala.spring.aspectj;

import com.alibaba.fastjson.JSON;
import com.wuhulala.spring.annotation.PrintLog;
import com.wuhulala.spring.annotation.PrintLog2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/10/28
 */
@Component
@Aspect
public class LogAspectj {
    private final static Logger logger = LoggerFactory.getLogger(LogAspectj.class);

    @Pointcut("@annotation(com.wuhulala.spring.annotation.PrintLog2)")
    public void log() {
    }

    @Before("log()&&@annotation(printLog)&&@annotation(printLog2)")
    public void before(JoinPoint joinPoint, PrintLog printLog, PrintLog2 printLog2) {
        logger.debug("printLog[" + printLog.desc() + "]--printLog2[" + printLog2.desc() + "]");
        logger.debug("target:[" + JSON.toJSONString(joinPoint.getTarget()) + "]");
        logger.debug("signature:[" + JSON.toJSONString(joinPoint.getSignature()) + "]");
        logger.debug("SourceLocation:[" + joinPoint.getSourceLocation() + "]");
        logger.debug("kind:[" + joinPoint.getKind() + "]");
        logger.debug("this:[" + joinPoint.getThis() + "]");  //就是被代理对象的实例，是同一个实例
        logger.debug("StaticPart:[" + joinPoint.getStaticPart() + "]");

        // logger.debug(printLog.desc() + "[" + joinPoint.getSignature().getName() + " : start.....]");
        // logger.debug(printLog2.desc() + "[" + joinPoint.getSignature().getName() + " : start.....]");
    }

    @After("log()")
    public void after(JoinPoint joinPoint) {
        logger.debug(joinPoint.getSignature().getName() + " : end.....");
    }
}
