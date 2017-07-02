 ```java
   //其实保存在BeanFactory的是workFactoryBean 实例
    //在获取bean的时候在beanName的的前面加上&符号即可获取workFactoryBean的实例
    //默认使用beanName返回的是getObject()返回的实例
    //在getObjectForBeanInstance方法中获取
    protected Object getObjectForBeanInstance(
            Object beanInstance, String name, String beanName, RootBeanDefinition mbd) {
        // 如果bean_name包含&前缀,并且bean实例不是factoryBean类型，抛出异常
        if (BeanFactoryUtils.isFactoryDereference(name) && !(beanInstance instanceof FactoryBean)) {
            throw new BeanIsNotAFactoryException(transformedBeanName(name), beanInstance.getClass());
        }

         //直接返回bean实例的两种情况
         // 1. beanInstance不是factoryBean类型的
         // 2. beanInstance是factoryBean类型的并且含有&前缀
        if (!(beanInstance instanceof FactoryBean) || BeanFactoryUtils.isFactoryDereference(name)) {
            return beanInstance;
        }

         //否则从factoryBean的getObject() 方法返回
        Object object = null;
        if (mbd == null) {
             //先从缓存中获取
            object = getCachedObjectForFactoryBean(beanName);
        }
        if (object == null) {

            FactoryBean<?> factory = (FactoryBean<?>) beanInstance;
            //如果当前bean定义为null，并且当前的BeanFactory包含beanName名称的Bean
            if (mbd == null && containsBeanDefinition(beanName)) {
                //如果BeanDefinition是childBeanDefinition的话 同时会合并父bean属性
                mbd = getMergedLocalBeanDefinition(beanName);
            }
            //是否是系统定义的factoryBean，如果是系统定义的，不需要进行后置处理，即BeanPostProcessor
            boolean synthetic = (mbd != null && mbd.isSynthetic());
            object = getObjectFromFactoryBean(factory, beanName, !synthetic);
        }
        return object;
    }


    private Object doGetObjectFromFactoryBean(final FactoryBean<?> factory, final String beanName)
            throws BeanCreationException {

        Object object;
        try {
            //如果系统的安全管理器不为空。判断当前线程是否有权限创建bean实例
            if (System.getSecurityManager() != null) {
                AccessControlContext acc = getAccessControlContext();
                try {
                    object = AccessController.doPrivileged(new PrivilegedExceptionAction<Object>() {
                        @Override
                        public Object run() throws Exception {
                            return factory.getObject();
                        }
                    }, acc);
                } catch (PrivilegedActionException pae) {
                    throw pae.getException();
                }
            } else {
                object = factory.getObject();
            }
        } catch (FactoryBeanNotInitializedException ex) {
            throw new BeanCurrentlyInCreationException(beanName, ex.toString());
        } catch (Throwable ex) {
            throw new BeanCreationException(beanName, "FactoryBean threw exception on object creation", ex);
        }

        //如果factoryBean返回的实例为null，或者当前实例正在创建中
        if (object == null && isSingletonCurrentlyInCreation(beanName)) {
            throw new BeanCurrentlyInCreationException(
                    beanName, "FactoryBean which is currently in creation returned null from getObject");
        }
        return object;
    }

```