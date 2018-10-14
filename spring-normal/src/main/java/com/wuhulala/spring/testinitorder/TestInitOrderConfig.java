package com.wuhulala.spring.testinitorder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/5/6
 * @link https://github.com/wuhulala
 */
@Configuration
public class TestInitOrderConfig {

    @Bean(initMethod = "initMethod",destroyMethod = "destroyMethod")
    public TestInitOrder testOrder(){
        return new TestInitOrder();
    }

}


