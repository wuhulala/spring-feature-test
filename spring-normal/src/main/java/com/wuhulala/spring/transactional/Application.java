package com.wuhulala.spring.transactional;

import com.wuhulala.api.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能说明:0o0<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/8/19<br>
 */
public class Application {


    ///////////////////////////// 方法区 ////////////////////////////////////
    public static void main(String[] args) {
        String location = "classpath:spring-transactional.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(location);
        context.start();
        UserService userService = context.getBean(UserService.class);
        userService.createUser(new User());
    }
}
