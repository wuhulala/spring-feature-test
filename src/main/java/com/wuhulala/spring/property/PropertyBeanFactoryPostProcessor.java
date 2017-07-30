package com.wuhulala.spring.property;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * author： wuhulala
 * date： 2017/7/30
 * version: 1.0
 * description: 作甚的
 */
@Component
public class PropertyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("====================ALL BEAN NAME LIST START========================[" + beanFactory.getBeanDefinitionCount()  + "]");
        for (String beanName : beanFactory.getBeanDefinitionNames()){
            System.out.println(beanName);
        }
        System.out.println("====================ALL BEAN NAME LIST END========================");
        System.out.println("==================================================================");
        System.out.println("==================================================================");
        System.out.println("==================================================================");
        System.out.println("==================================================================");


        System.out.println("====================SINGLE  BEAN NAME LIST START========================[" + beanFactory.getSingletonCount()  + "]");
        for (String beanName : beanFactory.getSingletonNames()){
            System.out.println(beanName);
        }
        System.out.println("====================SINGLE  BEAN NAME LIST END========================");
        //BeanDefinition environmentDefinition = beanFactory.getBeanDefinitionNames(Environment.class);
        //System.out.println(JSON.toJSONString(environmentDefinition));

    }
}
