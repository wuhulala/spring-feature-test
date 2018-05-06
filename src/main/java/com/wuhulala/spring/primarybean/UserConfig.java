package com.wuhulala.spring.primarybean;

import com.wuhulala.api.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 功能说明: com.wuhulala.spring.primarybean<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/2<br>
 */
@Configuration
public class UserConfig {

    @Primary
    @Bean
    public User user1(){
        return new User("user1");
    }

    @Bean
    public User user2(){
        return new User("user2");
    }


}
