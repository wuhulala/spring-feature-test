package com.wuhulala.studySpring.circledepend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;

/**
 * Created by xueah20964 on 2017/4/27.
 */
@Component
public class ShowBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Order {
    private static final Logger logger = LoggerFactory.getLogger(ShowBeanFactoryPostProcessor.class);

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;
        String[] beanNames = beanFactory.getBeanDefinitionNames();

        logger.debug("-----------------show beans start------------------");
        for (String beanName : beanNames) {
            BeanDefinition bd = beanFactory.getBeanDefinition(beanName);
            logger.debug(beanName + "：【" + bd + "】");
        }
        logger.debug("-----------------show beans end------------------");
    }

    @Override
    public int value() {
        return 1;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
