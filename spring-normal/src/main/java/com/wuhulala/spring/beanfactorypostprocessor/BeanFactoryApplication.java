package com.wuhulala.spring.beanfactorypostprocessor;

import com.wuhulala.api.User;
import com.wuhulala.spring.annotation.AoHuiAppAnnotation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by xueah20964 on 2017/4/24.
 */
@AoHuiAppAnnotation
public class BeanFactoryApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(BeanFactoryApplication.class);
        User user = (User) annotationConfigApplicationContext.getBean("user-wuhulala");
        System.out.println(user);
    }
}

