package com.wuhulala.studySpring;

import com.wuhulala.studySpring.testinitorder.TestInitOrder;
import com.wuhulala.studySpring.testnull.Worker;
import com.wuhulala.studySpring.testnull.WorkerFactoryBean;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author xueaohui
 */
public class Client {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        System.out.println("-------------- 初始化 完成 --------------------");

       /* MyFactory factory = context.getBean("myFactory", MyFactory.class);
        factory.createProduct();*/


        //无论是否是bean的单例作用域
        //先从singletonObjects(单例的object存储Map)从获取
        //如果为null 然后根据作用域初始化
        //否则直接返回缓存中的bean实例
        Worker worker = context.getBean("worker1", Worker.class);
        worker.doWork();

        System.out.println(worker);

        worker = context.getBean("worker1", Worker.class);
        worker.doWork();

        System.out.println(worker);

        worker = context.getBean("workerFactoryBean", Worker.class);

        System.out.println(worker);
        // 获取FactoryBean实例
        WorkerFactoryBean wfb = (WorkerFactoryBean) context.getBean("&workerFactoryBean");
        wfb.printName();
    }

    /**
     * 测试bean初始化与销毁方法调用顺序
     */
    @Test
    public void testInitOrder() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring1.xml");
        System.out.println("-------------- 初始化 完成 --------------------");
        context.getBean(TestInitOrder.class);
        context.close();

        //-------------------constructor-------------------
        //-------------------PostConstruct -------------------
        //-------------------init -------------------
        //-------------------init Method-------------------
        //-------------- 初始化 完成 --------------------
        //-------------------PreDestroy -------------------
        //-------------------destroy -------------------
        //-------------------destroy Method-------------------

        //以上顺序可以看出来
        //bean初始化方法顺序是 构造方法 ->@PostConstruct ->  InitializingBean -> init-method
        //@PreDestroy > DisposableBean > destroy-method

        //销毁方法
//        if (!CollectionUtils.isEmpty(this.beanPostProcessors)) {
        //1.这个集合中包括里面有一个CommonAnnotationBeanPostProcessor这执行了PreDestroy注解
//        for (DestructionAwareBeanPostProcessor processor : this.beanPostProcessors) {
//            processor.postProcessBeforeDestruction(this.bean, this.beanName);
//        }
//        }
//       //2.这里是判断是否实现了DisposableBean接口
//		if (this.invokeDisposableBean) {
//        if (logger.isDebugEnabled()) {
//            logger.debug("Invoking destroy() on bean with name '" + this.beanName + "'");
//        }
//        try {
//            if (System.getSecurityManager() != null) {
//                AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
//                    @Override
//                    public Object run() throws Exception {
//                        ((DisposableBean) bean).destroy();
//                        return null;
//                    }
//                }, acc);
//            }
//            else {
//                ((DisposableBean) bean).destroy();
//            }
//        }
//        catch (Throwable ex) {
//            String msg = "Invocation of destroy method failed on bean with name '" + this.beanName + "'";
//            if (logger.isDebugEnabled()) {
//                logger.warn(msg, ex);
//            }
//            else {
//                logger.warn(msg + ": " + ex);
//            }
//        }
//    }
//   // 3.1 判断destroyMethod方法是否为空，不为空 反射执行方法
//		if (this.destroyMethod != null) {
//        invokeCustomDestroyMethod(this.destroyMethod);
//    }
        //3.2 如果这个销毁方法名称不为空，从当前bean的Class信息中查找Method定义，如果查找的到，执行
//		else if (this.destroyMethodName != null) {
//        Method methodToCall = determineDestroyMethod();
//        if (methodToCall != null) {
//            invokeCustomDestroyMethod(methodToCall);
//        }
//    }
    }

    //测试循环依赖
    @Test
    public void testCircle(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring_circle.xml");
        //context.setAllowCircularReferences(false);
        //context.setAllowBeanDefinitionOverriding(true);

        context.start();
        //A a = context.getBean(A.class);
        //a.printAProperty();
        //A aa = a.getB().getA();
        //System.out.println( a == aa);
    }

}
