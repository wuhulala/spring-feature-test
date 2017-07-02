package com.wuhulala.studySpring.circledepend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Administrator
 * @version 1.0
 * @updateTime 2016/10/23
 */
@Component
public class B {
    @Autowired
    private A a;

    @Value("${asd.asdasd}")
    private String asd;


    private String qq;

    public B(){

    }

    public B(String qq) {
        this.qq =qq;
    }

    //构造器注入不能解决循环依赖
    //@Autowired
    public B(A a) {
        this.a = a;
    }

    public A getA() {
        return a;
    }

    public void setA(A a) {
        this.a = a;
    }

    public String getAsd() {
        return asd;
    }

    public void setAsd(String asd) {
        this.asd = asd;
    }

    @PostConstruct
    public void printAProperty() {
        System.out.println("B的asd属性----------------" + this.asd + "----------------");
        System.out.println("A的asd属性----------------" + a.getAsd() + "----------------");
    }
}
