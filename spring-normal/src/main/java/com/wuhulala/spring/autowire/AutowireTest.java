package com.wuhulala.spring.autowire;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 功能说明: com.wuhulala.spring.autowire<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/21<br>
 */
public class AutowireTest {
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-autowired-by-*.xml");
        UserContainer userContainer = context.getBean(UserContainer.class);
        System.out.println(userContainer);
    }

    //注入顺序 autowiredMode
    //=================================
    //   value   ||  含义
    //    0      || 默认的，加载顺序为先找construct => byName => byType
    //    1      || byName
    //    2      || byType
    //    3      || construct
    //    4      || 如果class有构造函数，使用constructor，否则使用byType
    //=================================
    // 如果没有指定autowire的类型
    // 它的
}
