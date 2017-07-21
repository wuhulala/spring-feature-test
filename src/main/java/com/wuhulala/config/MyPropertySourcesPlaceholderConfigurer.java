package com.wuhulala.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * 功能说明: com.wuhulala.config<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/20<br>
 */
public class MyPropertySourcesPlaceholderConfigurer extends PropertySourcesPlaceholderConfigurer implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
