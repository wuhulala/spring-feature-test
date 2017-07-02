package com.wuhulala.studySpring.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/10/28
 */
@Service
public class AService {
    @Transactional(propagation = Propagation.REQUIRED)
    public void a(){
        System.out.println("---------------a start--------------------");
        ((AService) AopContext.currentProxy()).b();
        System.out.println("---------------a end--------------------");
        //throw new RuntimeException("i want run , but i have some error");
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void b(){
        System.out.println("----------------b start-------------------");
        //throw new RuntimeException("i want run , but i have some error");
        System.out.println("----------------b end-------------------");
    }
}
