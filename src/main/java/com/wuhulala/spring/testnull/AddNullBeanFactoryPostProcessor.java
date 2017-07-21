package com.wuhulala.spring.testnull;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by xueah20964 on 2017/4/27.
 */
@Component
public class AddNullBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinitionRegistry bdr = (BeanDefinitionRegistry) beanFactory;
        RootBeanDefinition rbd = new RootBeanDefinition(
                Worker.class,
                AutowireCapableBeanFactory.AUTOWIRE_BY_TYPE, false);
        rbd.setLazyInit(true);
        rbd.setScope(ConfigurableBeanFactory.SCOPE_PROTOTYPE);
        bdr.registerBeanDefinition("worker1", rbd);

       /* Worker worker = new Worker();
        worker.setAge(1111111);
        worker.setName("worker1");
        beanFactory.registerSingleton("worker1", worker);*/
    }

    @Override
    public int getOrder() {
        return 999;
    }
}
