package com.wuhulala.spring.beanfactorypostprocessor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 功能
 *
 * @author xueah20964 2019/3/17 Create 1.0  <br>
 * @version 1.0
 */
@Component
public class YY {

    @Value("${user.name}")
    private String name;

    public void set(){

    }
}
