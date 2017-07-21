package com.wuhulala.spring.conditional;

import com.wuhulala.api.User;
import com.wuhulala.spring.conditional.kafka.annotation.KafkaOnEnabled;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明: com.wuhulala.spring.conditional<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/18<br>
 */
@Configuration
public class ConditionalTestConfig {

    @Bean
    @KafkaOnEnabled
    public User user(){
        return new User("aohui");
    }

}
