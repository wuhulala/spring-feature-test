package com.wuhulala.spring.xmlbeanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * author： wuhulala
 * date： 2017/8/13
 * version: 1.0
 * description: 作甚的
 */
public class EnvironmentAwareBean implements EnvironmentAware{
    @Autowired
    private Environment environment;

    @Autowired
    private BeanFactory beanFactory;
    public EnvironmentAwareBean(){
        System.out.println("-------------------初始化 EnvironmentAwareBean-----------------");
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("-------------------注入 Environment-----------------");
        this.environment = environment;
    }
}
