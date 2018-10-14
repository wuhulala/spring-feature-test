package com.wuhulala.spring.testinitorder;

import com.wuhulala.api.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class TestBeanFactoryPostProcessorOrder2 implements BeanFactoryPostProcessor,Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("我是Ordered 13.。。。。。beanPostPostProcessor start ...... 我只是定义下了bean");
        RootBeanDefinition rbd = new RootBeanDefinition();
        rbd.setTargetType(User.class);
        MutablePropertyValues values = new MutablePropertyValues();
        values.addPropertyValue("name", "wuhulala");
        rbd.setPropertyValues(values);
        ((DefaultListableBeanFactory)beanFactory).registerBeanDefinition("aaa", rbd);

    }

    @Override
    public int getOrder() {
        return 13;
    }
}