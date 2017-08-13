本spring教程设计spring的种种，最近看了源码之后，准备对自己这个一年多的学习进行一个总结式的教程，估计这个教程会延续非常之久，或许半年、或许一年，看到gayhub
上这种教程也不少了。但是好多都没有坚持到最后。我再在此保证两点：
1. 一周至少一篇，或源码分析，或spring最新官网新特性；
2. 不断更，只是为了给自己一个坚持的理由。

介绍下本人背景，一个刚刚毕业的应届生。学习JavaWeb开发加起来也即将有两年，中间做过些互联网开发，目前就职于一家传统的金融it企业。之前一直专注于应用的开发和打游戏。导致基础相对薄弱，知其然而不知其所以然。通过上半年的学习，对基础知识有了一定的了解之后。
又拜读了《深入理解java虚拟机》，《spring源码深度解析》等稍微偏基础的知识，

在此期间，我会了什么
1. 了解到了spring中的ioc的运作原理，代理模式以及动态代理的实现过程，远程服务的实现与通信过程；
2. SpringMvc的父子上下文以及对请求的映射处理。
3. java NIO与AIO的使用。阻塞与非阻塞，同步与异步的区别
4. spring 生命周期中的各种接口，如BeanFactoryPostProcessor、BeanPostProcessor等等
5. spring 循环依赖的处理
6. BeanFactory与FactoryBean
7. synchronized 在字节码层的体现，volatile关键字的指令不重排，保证可见性
8. Collection接口的集合类的部分源码
9. java内存布局与各种异常的实现方法
10. 双亲委托加载机制
11. 垃圾回收算法和各个垃圾回收器
12. jvisualvm的使用
13. 方法调用栈帧的变化
14. 并发包的各个类的使用

这次主要分享我学习spring源码过程中的收获以及在工作中的应用。
大致分为以下几个章节

1. bean
 1.1 bean定义
 
 1.2 FactoryBean
 
 1.3 bean的生命周期
 
 1.4 bean的类型
 
 1.5 bean的循环依赖
 
2. context

	2.1	prepareRefresh();

	2.2	obtainFreshBeanFactory

	2.3	prepareBeanFactory

	2.4 postProcessBeanFactory

	2.5 invokeBeanFactoryPostProcessors

2.6 registerBeanPostProcessors

2.7 initMessageSource

2.8 onRefresh

2.9 registerListeners

2.10 finishBeanFactoryInitialization

2.11 finishRefresh
 
3. aop
4. mvc
