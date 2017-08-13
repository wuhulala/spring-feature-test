package com.wuhulala.spring.xmlbeanfactory;

import org.springframework.beans.factory.BeanNameAware;

/**
 * author： wuhulala
 * date： 2017/8/13
 * version: 1.0
 * description: 作甚的
 */
public class BeanNameAwareBean implements BeanNameAware{
    public BeanNameAwareBean(){
        System.out.println("-------------------初始化 BeanNameAwareBean-----------------");
    }



    @Override
    public void setBeanName(String name) {
        System.out.println("-------------------注入 BeanName-----------------");
    }
}
