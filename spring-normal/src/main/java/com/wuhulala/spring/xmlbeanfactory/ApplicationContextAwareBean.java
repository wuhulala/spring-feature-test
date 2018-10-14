package com.wuhulala.spring.xmlbeanfactory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * author： wuhulala
 * date： 2017/8/13
 * version: 1.0
 * description: 作甚的
 */
public class ApplicationContextAwareBean implements ApplicationContextAware{

    public ApplicationContextAwareBean(){
        System.out.println("-------------------初始化 ApplicationContextAwareBean-----------------");
    }

    //会注入两遍
    @Autowired
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("--------------------注入 applicationContext --------------------------");
    }
}
