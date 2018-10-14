package com.wuhulala.spring.proxy.jdk;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 功能说明: 用户服务jdk代理处理器<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
public class UserServiceJdkProxyHandler implements InvocationHandler {


    private Object origin;

    public UserServiceJdkProxyHandler(Object o) {
        this.origin = o;
    }


    ///////////////////////////// 方法区 ////////////////////////////////////

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(origin, args);
        after();
        return result;
    }

    private void after() {
        System.out.println("after...");
    }

    private void before() {
        System.out.println("before...");
    }
}
