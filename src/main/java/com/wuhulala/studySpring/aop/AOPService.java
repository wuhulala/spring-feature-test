package com.wuhulala.studySpring.aop;

import com.wuhulala.studySpring.annotation.PrintLog;
import com.wuhulala.studySpring.annotation.PrintLog2;
import org.springframework.stereotype.Service;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/10/28
 */
@Service
public class AOPService {
    @PrintLog(desc = "i am a")
    @PrintLog2
    public void a(String name){
        System.out.println("---------------a start--------------------");
        System.out.println(this);
        //((AOPService) AopContext.currentProxy()).b();
        this.b("a");
        System.out.println("---------------a end--------------------");
    }

    @PrintLog(desc = "i am b")
    @PrintLog2
    public void b(String name){
        System.out.println("----------------b start-------------------");
        System.out.println("----------------b end-------------------");
    }
}
