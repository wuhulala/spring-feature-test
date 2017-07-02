package com.wuhulala.studySpring.testnull;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.stereotype.Component;

/**
 * Created by xueah20964 on 2017/4/27.
 */
@Component
public class WorkerFactoryBean implements FactoryBean<Worker> {


    public void printName() {
        System.out.println("my name is factory bean !!!");
    }

    @Override
    public Worker getObject() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Worker.class);
        //enhancer.setUseCache(false);// 关闭CGLib缓存，否则总是生成同一个类

        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println(o.getClass() + "[" + method.getName() + ":before]");
            Object result = methodProxy.invokeSuper(o, objects);
            System.out.println(o.getClass() + "[" + method.getName() + ":after]");
            return result;
        });

        return (Worker) enhancer.create();

       /* Worker worker = new Worker();
        worker.setAge(12);
        return worker;*/
    }

    @Override
    public Class<?> getObjectType() {
        return Worker.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


    public static void main(String[] args) {

    }

}

