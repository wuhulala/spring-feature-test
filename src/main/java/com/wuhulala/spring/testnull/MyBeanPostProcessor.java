package com.wuhulala.spring.testnull;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * beanPostProcessor 在bean初始化的时候才调用
 *
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/30
 * @link https://github.com/wuhulala
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor{
    @Override
    public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
        System.out.println(s + ":start...");
        return o;
    }

    @Override
    public Object postProcessAfterInitialization(Object o, String s) throws BeansException {
        System.out.println(s + ":end...");
        return o;
    }
}
