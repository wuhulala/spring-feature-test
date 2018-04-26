package com.wuhulala.flow.createbean;

import com.wuhulala.api.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuhulala
 * @version 1.0
 * @date 2017/12/3
 * @description 作甚的
 */
@Configuration
public class JavaConfigBeanCreator {
    @Bean
    public User user1(){
        return new User("user1");
    }

}
