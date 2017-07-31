package com.wuhulala.spring.property;

import com.wuhulala.api.User;
import com.wuhulala.spring.annotation.AoHuiAppAnnotation;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.MutablePropertySources;

/**
 * author： wuhulala
 * date： 2017/7/30
 * version: 1.0
 * description: 提前暴露properties
 */
@AoHuiAppAnnotation
@ComponentScan("com.wuhulala.api")
public class PropertyApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertyApplication.class);
        context.start();
        User user = context.getBean("user", User.class);
        System.out.println(user.getName());
    }

//    @Bean
//    public User user(){
//        System.out.println("===========================================create user=============================");
//        return new User();
//    }
}
