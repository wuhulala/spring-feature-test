package com.wuhulala.studySpring.testinitorder;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/5/6
 * @link https://github.com/wuhulala
 */
public class TestInitOrder implements InitializingBean, DisposableBean {

    public TestInitOrder() {
        System.out.println("-------------------constructor-------------------");
    }

    public void initMethod() {
        System.out.println("-------------------init Method-------------------");
    }

    public void destroyMethod() {
        System.out.println("-------------------destroy Method-------------------");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("-------------------init -------------------");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("-------------------destroy -------------------");

    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("-------------------PostConstruct -------------------");
    }

    @PreDestroy
    public void preDestroy()  {
        System.out.println("-------------------PreDestroy -------------------");
    }
//
//    -------------------constructor-------------------
//    -------------------PostConstruct -------------------
//    -------------------init -------------------
//    -------------------init Method-------------------
}
