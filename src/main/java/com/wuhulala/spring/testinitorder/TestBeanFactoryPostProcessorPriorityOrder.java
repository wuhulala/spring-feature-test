package com.wuhulala.spring.testinitorder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class TestBeanFactoryPostProcessorPriorityOrder implements BeanFactoryPostProcessor,PriorityOrdered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("我是PriorityOrdered 12.。。。。beanPostPostProcessor start ...... 我只是定义下了bean");

    }

    @Override
    public int getOrder() {
        return 12;
    }
}