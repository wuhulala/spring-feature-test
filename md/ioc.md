```java
ClassPathXmlApplicationContext.ClassPathXmlApplicationContext()
```
----
```java
public ClassPathXmlApplicationContext(String[] configLocations, boolean refresh, ApplicationContext parent)
			throws BeansException {
        //设置父亲上下文
		super(parent); 
		//设置配置文件路径
		setConfigLocations(configLocations);
		if (refresh) {
		    //刷新
			refresh();
		}
	}
}
```
----
```java
//refresh方法
public void refresh() throws BeansException, IllegalStateException {
        //根据信号startupShutdownMonitor来作同步，在同步代码块中进行refresh操作
		synchronized (this.startupShutdownMonitor) {
			//1.设置启动时间
            //2.设置上下文激活
            //3.打印启动日志
            //4.初始化容器上下文中的property文件(空方法，由子类覆盖实现)
            //5.判断容器上下文所需要的属性存不存在
			prepareRefresh();

            //告诉子类更新内部的beanfactory
            //获取一个新的beanfactory
			ConfigurableListableBeanFactory beanFactory = obtainFreshBeanFactory();

            //配置上下文  比如类加载器
			prepareBeanFactory(beanFactory);

			try {

				// 为容器某些子类扩展共你呢个所用
				postProcessBeanFactory(beanFactory);

				// 注册Bean
				invokeBeanFactoryPostProcessors(beanFactory);

				// 注册所有实现了BeanPostProcessor接口的Bean
				registerBeanPostProcessors(beanFactory);

                //初始化MessageSource，和国际化相关
				initMessageSource();

                //初始化容器事件传播器
				initApplicationEventMulticaster();

               //调用容器子类某些特殊Bean的初始化，模板方法
				onRefresh();

               //为事件传播器注册监听器
				registerListeners();

               //初始化所有剩余的bean（普通bean）
				finishBeanFactoryInitialization(beanFactory);

                //初始化容器的生命周期事件处理器，并发布容器的生命周期事件
				finishRefresh();
			}

			catch (BeansException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn("Exception encountered during context initialization - " +
							"cancelling refresh attempt: " + ex);
				}

				// Destroy already created singletons to avoid dangling resources.
				destroyBeans();

				// Reset 'active' flag.
				cancelRefresh(ex);

				// Propagate exception to caller.
				throw ex;
			}

			finally {
				// Reset common introspection caches in Spring's core, since we
				// might not ever need metadata for singleton beans anymore...
				resetCommonCaches();
			}
		}
	}
```
----
```java
//registerBeanPostProcessors()方法 注册实现BeanPostProcessor接口的Bean
MyBeanPostProcessor
//1.  postProcessBeforeInitialization(Object bean, String beanName)方法将在调用bean的初始化方法之前被调用。方法参数分别表示当前的bean对象和对应的bean名称。
//2.  postProcessAfterInitialization(Object bean, String beanName)方法将在调用bean的初始化方法之后被调用。
    public static void registerBeanPostProcessors(
            ConfigurableListableBeanFactory beanFactory, AbstractApplicationContext applicationContext) {
        //获取实现BeanPostProcessor接口的Bean
        String[] postProcessorNames = beanFactory.getBeanNamesForType(BeanPostProcessor.class, true, false);

        
        //添加并实例化一个BeanPostProcessor，这个是用来记录一些信息的
        int beanProcessorTargetCount = beanFactory.getBeanPostProcessorCount() + 1 + postProcessorNames.length;
        beanFactory.addBeanPostProcessor(new BeanPostProcessorChecker(beanFactory, beanProcessorTargetCount));

        //分开所有实现BeanPostProcessor接口的Bean 优先级依此是PriorityOrdered,
        // Ordered, and the rest.
        List<BeanPostProcessor> priorityOrderedPostProcessors = new ArrayList<BeanPostProcessor>();
        List<BeanPostProcessor> internalPostProcessors = new ArrayList<BeanPostProcessor>();
        List<String> orderedPostProcessorNames = new ArrayList<String>();
        List<String> nonOrderedPostProcessorNames = new ArrayList<String>();
        for (String ppName : postProcessorNames) {
            if (beanFactory.isTypeMatch(ppName, PriorityOrdered.class)) {
                BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
                priorityOrderedPostProcessors.add(pp);
                if (pp instanceof MergedBeanDefinitionPostProcessor) {
                    internalPostProcessors.add(pp);
                }
            }
            else if (beanFactory.isTypeMatch(ppName, Ordered.class)) {
                orderedPostProcessorNames.add(ppName);
            }
            else {
                nonOrderedPostProcessorNames.add(ppName);
            }
        }

        // 首先注册实现  PriorityOrdered. 接口的
        sortPostProcessors(beanFactory, priorityOrderedPostProcessors);
        registerBeanPostProcessors(beanFactory, priorityOrderedPostProcessors);

        // 注册实现 Ordered.接口的
        List<BeanPostProcessor> orderedPostProcessors = new ArrayList<BeanPostProcessor>();
        for (String ppName : orderedPostProcessorNames) {
            BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
            orderedPostProcessors.add(pp);
            if (pp instanceof MergedBeanDefinitionPostProcessor) {
                internalPostProcessors.add(pp);
            }
        }
        sortPostProcessors(beanFactory, orderedPostProcessors);
        registerBeanPostProcessors(beanFactory, orderedPostProcessors);

        // 
        List<BeanPostProcessor> nonOrderedPostProcessors = new ArrayList<BeanPostProcessor>();
        for (String ppName : nonOrderedPostProcessorNames) {
            BeanPostProcessor pp = beanFactory.getBean(ppName, BeanPostProcessor.class);
            nonOrderedPostProcessors.add(pp);
            if (pp instanceof MergedBeanDefinitionPostProcessor) {
                internalPostProcessors.add(pp);
            }
        }
        registerBeanPostProcessors(beanFactory, nonOrderedPostProcessors);

        // 注册所有的
        sortPostProcessors(beanFactory, internalPostProcessors);
        registerBeanPostProcessors(beanFactory, internalPostProcessors);

        beanFactory.addBeanPostProcessor(new ApplicationListenerDetector(applicationContext));
    }
```
----
```java
    //初始化消息
    //如果没有就用父级元素的
    protected void initMessageSource() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        if (beanFactory.containsLocalBean(MESSAGE_SOURCE_BEAN_NAME)) {
            this.messageSource = beanFactory.getBean(MESSAGE_SOURCE_BEAN_NAME, MessageSource.class);
            // Make MessageSource aware of parent MessageSource.
            if (this.parent != null && this.messageSource instanceof HierarchicalMessageSource) {
                HierarchicalMessageSource hms = (HierarchicalMessageSource) this.messageSource;
                if (hms.getParentMessageSource() == null) {
                    // Only set parent context as parent MessageSource if no parent MessageSource
                    // registered already.
                    hms.setParentMessageSource(getInternalParentMessageSource());
                }
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Using MessageSource [" + this.messageSource + "]");
            }
        }
        else {
            // Use empty MessageSource to be able to accept getMessage calls.
            DelegatingMessageSource dms = new DelegatingMessageSource();
            dms.setParentMessageSource(getInternalParentMessageSource());
            this.messageSource = dms;
            beanFactory.registerSingleton(MESSAGE_SOURCE_BEAN_NAME, this.messageSource);
            if (logger.isDebugEnabled()) {
                logger.debug("Unable to locate MessageSource with name '" + MESSAGE_SOURCE_BEAN_NAME +
                        "': using default [" + this.messageSource + "]");
            }
        }
    }
```

```java
//初始化所有单例非延迟Bean
public void preInstantiateSingletons() throws BeansException {
        if(this.logger.isDebugEnabled()) {
            this.logger.debug("Pre-instantiating singletons in " + this);
        }

        ArrayList beanNames = new ArrayList(this.beanDefinitionNames);
        Iterator var2 = beanNames.iterator();

        while(true) {
            while(true) {
                String beanName;
                RootBeanDefinition singletonInstance;
                do {
                    do {
                        do {
                            if(!var2.hasNext()) {
                                var2 = beanNames.iterator();

                                while(var2.hasNext()) {
                                    beanName = (String)var2.next();
                                    Object singletonInstance1 = this.getSingleton(beanName);
                                    if(singletonInstance1 instanceof SmartInitializingSingleton) {
                                        final SmartInitializingSingleton smartSingleton1 = (SmartInitializingSingleton)singletonInstance1;
                                        if(System.getSecurityManager() != null) {
                                            AccessController.doPrivileged(new PrivilegedAction() {
                                                public Object run() {
                                                    smartSingleton1.afterSingletonsInstantiated();
                                                    return null;
                                                }
                                            }, this.getAccessControlContext());
                                        } else {
                                            smartSingleton1.afterSingletonsInstantiated();
                                        }
                                    }
                                }

                                return;
                            }

                            beanName = (String)var2.next();
                            singletonInstance = this.getMergedLocalBeanDefinition(beanName);
                        } while(singletonInstance.isAbstract());
                    } while(!singletonInstance.isSingleton());
                } while(singletonInstance.isLazyInit());

                if(this.isFactoryBean(beanName)) {
                    final FactoryBean smartSingleton = (FactoryBean)this.getBean("&" + beanName);
                    boolean isEagerInit;
                    if(System.getSecurityManager() != null && smartSingleton instanceof SmartFactoryBean) {
                        isEagerInit = ((Boolean)AccessController.doPrivileged(new PrivilegedAction() {
                            public Boolean run() {
                                return Boolean.valueOf(((SmartFactoryBean)smartSingleton).isEagerInit());
                            }
                        }, this.getAccessControlContext())).booleanValue();
                    } else {
                        isEagerInit = smartSingleton instanceof SmartFactoryBean && ((SmartFactoryBean)smartSingleton).isEagerInit();
                    }

                    if(isEagerInit) {
                        this.getBean(beanName);
                    }
                } else {
                    this.getBean(beanName);
                }
            }
        }
    }
```