package com.wuhulala.studySpring.conditional;

import com.wuhulala.api.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明: com.wuhulala.studySpring.conditional<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/18<br>
 */
@Configuration
public class ConditionalTestConfig {

    @Bean
    @Conditional(KafkaEnableConditional.class)
    public User user(){
        return new User("aohui");
    }

}
