package com.wuhulala.spring.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 功能说明: Jdk代理工厂<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
public class JdkProxyFactory {

    ///////////////////////////// 方法区 ////////////////////////////////////

    /**
     * 创建代理
     *
     * @param originInstance 源实例
     * @param handler 代理处理器
     * @return 代理
     */
    public static Object createProxy(Object originInstance, InvocationHandler handler){
        return Proxy.newProxyInstance(
                originInstance.getClass().getClassLoader(),
                originInstance.getClass().getInterfaces(),
                handler);
    }
}
