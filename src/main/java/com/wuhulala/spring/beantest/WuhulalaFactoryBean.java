package com.wuhulala.spring.beantest;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

/**
 * author： wuhulala
 * date： 2017/7/2
 * version: 1.0
 * description: 作甚的
 */
@Component
public class WuhulalaFactoryBean implements FactoryBean<Wuhulala>{
    @Override
    public Wuhulala getObject() throws Exception {
        System.out.println("---------Wuhulala getObject-----------");
        return new Wuhulala("wuhulala1");
    }

    public Wuhulala getObject2(){
        System.out.println("---------Wuhulala getObject2-----------");

        return new Wuhulala("wuhulala2");
    }


    @Override
    public Class<?> getObjectType() {
        return Wuhulala.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
