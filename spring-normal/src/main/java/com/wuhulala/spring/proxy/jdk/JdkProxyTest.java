package com.wuhulala.spring.proxy.jdk;

import com.wuhulala.api.service.UserService;
import com.wuhulala.spring.proxy.UserServiceImpl;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
public class JdkProxyTest {


    ///////////////////////////// 方法区 ////////////////////////////////////

    public static void main(String[] args) {

        //设置保留生成的动态代理类的Class文件 必须放在前面
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");

        UserService userService = new UserServiceImpl();
        UserServiceJdkProxyHandler proxyHandler = new UserServiceJdkProxyHandler(userService);
        UserService proxy = (UserService) JdkProxyFactory.createProxy(userService, proxyHandler);

        proxy.createUser("wuhulala");
    }
}
