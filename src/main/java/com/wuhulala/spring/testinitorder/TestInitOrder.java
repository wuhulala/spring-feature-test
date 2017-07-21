package com.wuhulala.spring.testinitorder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/5/6
 * @link https://github.com/wuhulala
 */
public class TestInitOrder implements InitializingBean, DisposableBean,BeanNameAware,ApplicationContextAware {

    private String name;

    public TestInitOrder() {
        System.out.println("-------------------constructor-------------------");
    }

    public void initMethod() {
        this.name = "asdasdasd";
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

    @Override
    public void setBeanName(String s) {
        System.out.println("bean name is .." + s);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AutowireCapableBeanFactory factory = applicationContext.getAutowireCapableBeanFactory();
        TestInitOrder test = factory.getBean(TestInitOrder.class);
        System.out.println(test.name);
    }
//    ----- PriorityOrdered 的 beanFactoryPostProcessor--------------------
//    ----- Ordered 的 beanFactoryPostProcessor--------------------
//    -------------------constructor------------------
//  -----------------Aware 接口[因为执行这些注入的都是beanpostprocessor]---------------------
//    -------------------PostConstruct -------------------
//    -------------------init -------------------
//    -------------------init Method-------------------
}
