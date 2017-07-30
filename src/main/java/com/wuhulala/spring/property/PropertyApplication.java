package com.wuhulala.spring.property;

import com.wuhulala.api.User;
import com.wuhulala.spring.annotation.AoHuiAppAnnotation;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * author： wuhulala
 * date： 2017/7/30
 * version: 1.0
 * description: 提前暴露properties
 */
@AoHuiAppAnnotation
public class PropertyApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PropertyApplication.class);
        context.start();
    }

    @Bean
    public User user(){
        return new User();
    }
}
