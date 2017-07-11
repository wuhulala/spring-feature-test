package com.wuhulala.studySpring.testinitorder;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class TestBeanFactoryPostProcessorOrder2 implements BeanFactoryPostProcessor,Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("我是Ordered 13.。。。。。beanPostPostProcessor start ...... 我只是定义下了bean");

    }

    @Override
    public int getOrder() {
        return 13;
    }
}