package com.wuhulala.spring.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * 功能说明: com.wuhulala.spring.autowire<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/21<br>
 */
public class AutowireTest {
    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-autowired-by-name.xml");
        UserContainer userContainer = context.getBean(UserContainer.class);
        System.out.println(userContainer);
        System.out.println(userContainer.getUser().getName());
        while (true){
            TimeUnit.SECONDS.sleep(10);
        }
    }
}
