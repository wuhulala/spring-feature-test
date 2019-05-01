package com.wuhulala.spring.beanfactorypostprocessor;

import com.wuhulala.api.Address;
import com.wuhulala.api.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@Component
public class ModifyBeanBeanFactoryPostProcessor implements BeanFactoryPostProcessor, PriorityOrdered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        RootBeanDefinition bd = (RootBeanDefinition) beanFactory.getBeanDefinition("user-wuhulala");
        MutablePropertyValues values = new MutablePropertyValues();
        values.addPropertyValue("name", "${my.user.name}");
        values.addPropertyValue("age", 12);
        Address address = new Address();
        address.setName("浙江省");
        values.addPropertyValue("address", address);
        bd.setPropertyValues(values);
    }
}