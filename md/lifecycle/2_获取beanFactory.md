1. 执行上下文的真正刷新
2. 判断是否已经有beanfactory再刷新，如果在刷新的话，注销bean，然后关闭工厂。
3. 创建一个新的BeanFactory（DefaultListableBeanFactory），如果有父BeanFactory的话，设置为当前上下文的父上下文属性。并且进行相应的配置
4. 定制BeanFactory，目前有两个配置，一个是循环依赖，另一个是bean覆盖，默认都是允许的，只能通过硬编码的方式进行配置

4.1 beanFactory.setAllowBeanDefinitionOverriding //Bean覆盖

4.2 beanFactory.setAllowCircularReferences // 循环依赖

5. 加载Bean定义

//在使用xml配置启动的时候
5.1 定义XmlBeanDefinitionReader，并设置Environment,ResourceLoader,设置EntityResolver，默认使用ResourceEntityResolver

5.2 真正的开始加载bean定义

5.2.1 读取配置文件目录信息

5.2.2 遍历配置文件读取BeanDefinition，并记录个数

5.2.2.1 xml配置参数目录对照

 ```<context:annotation-config/>```或者```<context:component-scan/>```  bean-name：internalConfigurationAnnotationProcessor bean-class：ConfigurationClassPostProcessor  用来处理@Configuration注解的类
 ```<context:property-placeholder>``` bean-name：PropertySourcesPlaceholderConfigurer#0 bean-class：PropertySourcesPlaceholderConfigurer 用来处理${}属性占位符

  默认会有以下四个后置处理器，和一个事件监听器的工厂
 org.springframework.context.event.internalEventListenerFactory
 org.springframework.context.event.internalEventListenerProcessor
 org.springframework.context.annotation.internalAutowiredAnnotationProcessor
 org.springframework.context.annotation.internalCommonAnnotationProcessor
 org.springframework.context.annotation.internalRequiredAnnotationProcessor

