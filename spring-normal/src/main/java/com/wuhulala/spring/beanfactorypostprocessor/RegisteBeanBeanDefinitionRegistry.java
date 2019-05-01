package com.wuhulala.spring.beanfactorypostprocessor;

import com.wuhulala.api.Address;
import com.wuhulala.api.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
public class RegisteBeanBeanDefinitionRegistry implements BeanDefinitionRegistryPostProcessor,Ordered {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        RootBeanDefinition rbd = new RootBeanDefinition();
        rbd.setTargetType(User.class);
        rbd.setBeanClass(User.class);
        registry.registerBeanDefinition("user-wuhulala", rbd);
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
       // do nothing
    }
}