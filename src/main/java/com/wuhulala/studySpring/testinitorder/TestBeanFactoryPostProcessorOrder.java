package com.wuhulala.studySpring.testinitorder;

import com.wuhulala.studySpring.beantest.Wuhulala;
import com.wuhulala.studySpring.beantest.WuhulalaFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class TestBeanFactoryPostProcessorOrder implements BeanFactoryPostProcessor,Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("我是Ordered 12.。。。。beanPostPostProcessor start ...... 我只是定义下了bean");

    }

    @Override
    public int getOrder() {
        return 12;
    }
}