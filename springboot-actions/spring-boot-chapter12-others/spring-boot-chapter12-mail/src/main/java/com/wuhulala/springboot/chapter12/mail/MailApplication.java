package com.wuhulala.springboot.chapter12.mail;

import com.wuhulala.springboot.chapter12.mail.service.MailService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/4/13<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@SpringBootApplication
public class MailApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MailApplication.class, args);
        context.getBean(MailService.class).send("xxxx@qq.com", "<h3>hello wuhulala</h3>");
    }

}
