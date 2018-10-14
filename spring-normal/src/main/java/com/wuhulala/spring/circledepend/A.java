package com.wuhulala.spring.circledepend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author Administrator
 * @version 1.0
 * @updateTime 2016/10/23
 */
//@Lazy//设置为LAZY后延迟加载，加载的时候并不会判断A被判断出循环依赖
@Component
public class A {
    @Autowired
    private B b;
    @Value("${asd.asdasd}")
    private String asd;



    public A(){

    }
    //@Autowired
    public A(B b){
        this.b = b;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public String getAsd() {
        return asd;
    }

    public void setAsd(String asd) {
        this.asd = asd;
    }

    @PostConstruct
    public void printAProperty(){
        System.out.println("---------------A打印-----------");
        System.out.println("A的asd属性----------------" + this.asd + "----------------");
        System.out.println("B的asd属性----------------" + b.getAsd() + "----------------");
        System.out.println("---------------B打印-----------");
        b.printAProperty();
    }

}
