package com.wuhulala.spring.xmlbeanfactory;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 功能说明: 测试xml环境配置加载beanFactory<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/8/12<br>
 */
public class XmlBeanFactoryTest {
    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:xml-bean-factory.xml");
        //context.addApplicationListener();
        context.start();
        while(true){
            TimeUnit.SECONDS.sleep(5);
            System.out.println("i am still alive.....");
        }
    }
}
