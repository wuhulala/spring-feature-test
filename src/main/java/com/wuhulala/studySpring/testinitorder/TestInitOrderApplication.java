package com.wuhulala.studySpring.testinitorder;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * author： wuhulala
 * date： 2017/7/2
 * version: 1.0
 * description: 作甚的
 */
@Configuration
@ComponentScan
public class TestInitOrderApplication {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestInitOrderApplication.class);

        context.start();

    }
}
