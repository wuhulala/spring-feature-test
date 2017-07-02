package com.wuhulala.studySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author xueaohui
 */
@Component
public class HelloProxy {
    @Autowired
    private HelloImpl hello;

    public void sayHello(String name){
        System.out.println("----------proxy start----------");
        hello.say(name);
        System.out.println("----------proxy end----------");

    }
}
