package com.wuhulala.spring.property;

import com.wuhulala.api.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.*;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * author： wuhulala
 * date： 2017/7/30
 * version: 1.0
 * description: <p>在一个beanPostProcessor工作之前，会把他自己初始化完成，初始化完成后是一个经过所有加工的bean，
 * 如果此beanPostProcessor依赖于其它的bean，会导致它自己不工作。
 * 被依赖的bean(需要经过BeanPostProcessor前置和后置处理的)会检查是否被所有的beanPostProcessor处理过了。所以会抛出"[被依赖的bean没有被所有beanPostProcessor的处理]"的警告
 * ；所以最好不要依赖其它的bean，通过aware接口拿到想要的
 * </p>
 */
@Component
public class ServiceBeanPostProcessor implements BeanPostProcessor, InitializingBean, ApplicationContextAware, EnvironmentAware {

   // @Autowired
    //private User user;

    private Environment environment;

//    public ServiceBeanPostProcessor(Environment environment, User user){
//        this.user = user;
//        this.environment = environment;
//        //System.out.println("constructor " + this.user.getName());
//    }


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.print("processor " + beanName + "  before ：：：：：：  ");
        if (bean instanceof User) {
            System.out.print("user name is " + ((User) bean).getName());
        }
        System.out.println("  ");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.print("processor " + beanName + "  after ：：：：：：  ");

        if (bean instanceof User) {
            System.out.println("user name is " + ((User) bean).getName());
        }
        System.out.println("  ");

        return bean;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("serviceBeanPostProcessor is init ..." + environment.getProperty("my.user.name"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("set applicationContext  ..." + applicationContext.getApplicationName());
    }

    public void setEnvironment(Environment environment) {
        System.out.println("set environment  ..." );
        this.environment = environment;
    }

}
