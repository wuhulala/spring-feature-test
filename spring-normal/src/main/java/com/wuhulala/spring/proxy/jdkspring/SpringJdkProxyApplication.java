package com.wuhulala.spring.proxy.jdkspring;

import com.wuhulala.api.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
@ComponentScan
@EnableAspectJAutoProxy
public class SpringJdkProxyApplication {


    ///////////////////////////// 方法区 ////////////////////////////////////
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringJdkProxyApplication.class);


        //Object proxy = context.getBean("&mySpringUserServiceImpl");
        UserService userService = context.getBean(UserService.class);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        userService.createUser("wuhulala");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }
}
