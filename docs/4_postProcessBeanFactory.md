1. 子类对beanFactory的处理。据目前来看，只有web环境才重写了这个方法。

比如web环境下的Context对这个方法的重写就是添加了几个个性化的配置
```
// 一个servlet上下文环境的Bean后置处理器
beanFactory.addBeanPostProcessor(new ServletContextAwareProcessor(this.servletContext, this.servletConfig));
//Aware接口的忽略

beanFactory.ignoreDependencyInterface(ServletContextAware.class);
beanFactory.ignoreDependencyInterface(ServletConfigAware.class);
WebApplicationContextUtils.registerWebApplicationScopes(beanFactory, this.servletContext);
WebApplicationContextUtils.registerEnvironmentBeans(beanFactory, this.servletContext, this.servletConfig);
```