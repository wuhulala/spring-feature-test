package com.wuhulala.spring.primarybean;

import com.wuhulala.spring.annotation.AoHuiAppAnnotation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xueah20964 on 2017/4/24.
 */
@AoHuiAppAnnotation
public class Application {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Application.class);
        UserFactory userFactory = annotationConfigApplicationContext.getBean("userFactory", UserFactory.class);
        userFactory.print();
    }
}

