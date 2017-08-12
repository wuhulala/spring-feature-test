package com.wuhulala.spring.xmlbeanfactory;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能说明: com.wuhulala.spring.xmlbeanfactory<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/8/12<br>
 */
public class XmlBeanFactoryTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:xml-bean-factory.xml");
        context.start();
    }
}
