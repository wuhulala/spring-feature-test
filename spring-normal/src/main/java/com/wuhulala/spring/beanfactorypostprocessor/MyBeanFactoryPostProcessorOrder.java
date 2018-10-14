package com.wuhulala.spring.beanfactorypostprocessor;

import com.wuhulala.api.Address;
import com.wuhulala.api.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class MyBeanFactoryPostProcessorOrder implements BeanDefinitionRegistryPostProcessor,Ordered {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition rbd = new RootBeanDefinition();
        rbd.setTargetType(User.class);
        MutablePropertyValues values = new MutablePropertyValues();
        values.addPropertyValue("name", "wuhulala");
        values.addPropertyValue("age", 12);
        Address address = new Address();
        address.setName("浙江省");
        values.addPropertyValue("address", address);
        rbd.setBeanClass(User.class);
        rbd.setPropertyValues(values);
        registry.registerBeanDefinition("aaa", rbd);
    }

    @Override
    public int getOrder() {
        return 13;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}