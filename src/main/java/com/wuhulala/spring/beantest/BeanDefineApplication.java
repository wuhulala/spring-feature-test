package com.wuhulala.spring.beantest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * author： wuhulala
 * date： 2017/7/2
 * version: 1.0
 * description: 作甚的
 */
@ComponentScan
public class BeanDefineApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanDefineApplication.class);

        context.start();

    }
}
