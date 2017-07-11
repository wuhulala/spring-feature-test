package com.wuhulala.studySpring.beantest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

/**
 * author： wuhulala
 * date： 2017/7/2
 * version: 1.0
 * description: 作甚的
 */
@Component
public class BeanDefineTest implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;
        beanFactory.getBean(WuhulalaFactoryBean.class);
        String[] wuhulalaBeanNames = beanFactory.getBeanNamesForType(WuhulalaFactoryBean.class);
        for (String name:wuhulalaBeanNames) {
            System.out.println(name);
        }
        System.out.println("---------------add start--------------------");
        RootBeanDefinition rbd = new RootBeanDefinition(
                Wuhulala.class,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        rbd.setFactoryBeanName("&wuhulalaFactoryBean");
        rbd.setFactoryMethodName("getObject2");
        //rbd.setParentName();
        //rbd.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
        bdr.registerBeanDefinition("factoryBean2", rbd);
        System.out.println("---------------add end--------------------");

    }
}
