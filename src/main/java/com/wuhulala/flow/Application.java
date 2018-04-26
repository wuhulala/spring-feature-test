package com.wuhulala.flow;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author wuhulala
 * @version 1.0
 * @date 2017/12/3
 * @description 作甚的
 */
public class Application {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:xml-bean-creator.xml");
        String xx = (String) context.getBean("string");
        System.out.println(xx);

    }
}
