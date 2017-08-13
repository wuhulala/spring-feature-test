package com.wuhulala.spring.xmlbeanfactory;

import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

/**
 * author： wuhulala
 * date： 2017/8/13
 * version: 1.0
 * description: 作甚的
 */
public class NoAwareBean implements EnvironmentAware{

    private EnvironmentAware environmentAware;

    private BeanNameAwareBean beanNameAwareBean;

    private ApplicationContextAwareBean applicationContextAwareBean;

    public BeanNameAwareBean getBeanNameAwareBean() {
        return beanNameAwareBean;
    }

    public void setBeanNameAwareBean(BeanNameAwareBean beanNameAwareBean) {
        this.beanNameAwareBean = beanNameAwareBean;
    }

    public EnvironmentAware getEnvironmentAware() {
        return environmentAware;
    }

    public void setEnvironmentAware(EnvironmentAware environmentAware) {
        this.environmentAware = environmentAware;
    }


    public ApplicationContextAwareBean getApplicationContextAwareBean() {
        return applicationContextAwareBean;
    }

    public void setApplicationContextAwareBean(ApplicationContextAwareBean applicationContextAwareBean) {
        this.applicationContextAwareBean = applicationContextAwareBean;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("----------------------------------------NoAwareBean  environment--------------------------------------------------");
    }
}
