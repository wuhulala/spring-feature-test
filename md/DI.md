```java
public <T> T getBean(Class<T> requiredType, Object... args) throws BeansException {
        Assert.notNull(requiredType, "Required type must not be null");
        String[] beanNames = this.getBeanNamesForType(requiredType);
        String[] primaryCandidate;
        int priorityCandidate;
        int var7;
        String beanName;
        if(beanNames.length > 1) {
            ArrayList candidates = new ArrayList();
            primaryCandidate = beanNames;
            priorityCandidate = beanNames.length;

            for(var7 = 0; var7 < priorityCandidate; ++var7) {
                beanName = primaryCandidate[var7];
                //如果没有被实例化并且没有被其他bean需求过，添加bean
                if(!this.containsBeanDefinition(beanName) || this.getBeanDefinition(beanName).isAutowireCandidate()) {
                    candidates.add(beanName);
                }
            }

            if(candidates.size() > 0) {
                beanNames = (String[])candidates.toArray(new String[candidates.size()]);
            }
        }

        if(beanNames.length == 1) {
            return this.getBean(beanNames[0], requiredType, args);
        } else if(beanNames.length <= 1) {
            if(this.getParentBeanFactory() != null) {
                return this.getParentBeanFactory().getBean(requiredType, args);
            } else {
                throw new NoSuchBeanDefinitionException(requiredType);
            }
        } else {
            HashMap var9 = new HashMap();
            primaryCandidate = beanNames;
            priorityCandidate = beanNames.length;

            for(var7 = 0; var7 < priorityCandidate; ++var7) {
                beanName = primaryCandidate[var7];
                var9.put(beanName, this.getBean(beanName, requiredType, args));
            }

            String var10 = this.determinePrimaryCandidate(var9, requiredType);
            if(var10 != null) {
                return this.getBean(var10, requiredType, args);
            } else {
                String var11 = this.determineHighestPriorityCandidate(var9, requiredType);
                if(var11 != null) {
                    return this.getBean(var11, requiredType, args);
                } else {
                    throw new NoUniqueBeanDefinitionException(requiredType, var9.keySet());
                }
            }
        }
    }
```
---
```java
//根据type获取所有的bean
public String[] getBeanNamesForType(Class<?> type, boolean includeNonSingletons, boolean allowEagerInit) {
        if(this.isConfigurationFrozen() && type != null && allowEagerInit) {
            //从cache中寻找
            Map cache = includeNonSingletons?this.allBeanNamesByType:this.singletonBeanNamesByType;
            String[] resolvedBeanNames = (String[])cache.get(type);
            //如果寻找到了 那么返回
            if(resolvedBeanNames != null) {
                return resolvedBeanNames;
            } else {
                //根据父亲继续找
                resolvedBeanNames = this.doGetBeanNamesForType(ResolvableType.forRawClass(type), includeNonSingletons, true);
                if(ClassUtils.isCacheSafe(type, this.getBeanClassLoader())) {
                    cache.put(type, resolvedBeanNames);
                }

                return resolvedBeanNames;
            }
        } else {
            return this.doGetBeanNamesForType(ResolvableType.forRawClass(type), includeNonSingletons, allowEagerInit);
        }
    }
```

```java
//获取bean
protected <T> T doGetBean(String name, Class<T> requiredType, final Object[] args, boolean typeCheckOnly) throws BeansException {
        final String beanName = this.transformedBeanName(name);
        //先从缓存中获取bean处理那些已经被创建过的单例bean 
        //对这种bean不需要重复创建
        Object sharedInstance = this.getSingleton(beanName);
        Object bean;
        if(sharedInstance != null && args == null) {
            if(this.logger.isDebugEnabled()) {
                if(this.isSingletonCurrentlyInCreation(beanName)) {
                    this.logger.debug("Returning eagerly cached instance of singleton bean \'" + beanName + "\' that is not fully initialized yet - a consequence of a circular reference");
                } else {
                    this.logger.debug("Returning cached instance of singleton bean \'" + beanName + "\'");
                }
            }
            //如果存在 那么从返回bean实例
            bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, (RootBeanDefinition)null);
        } else {
            if(this.isPrototypeCurrentlyInCreation(beanName)) {
                throw new BeanCurrentlyInCreationException(beanName);
            }
            //如果没有从单例的bean获取到 那么从父beanfactory中获取
            BeanFactory ex = this.getParentBeanFactory();
            if(ex != null && !this.containsBeanDefinition(beanName)) {
                String var24 = this.originalBeanName(name);
                if(args != null) {
                    return ex.getBean(var24, args);
                }

                return ex.getBean(var24, requiredType);
            }

            if(!typeCheckOnly) {
                this.markBeanAsCreated(beanName);
            }

            try {
                //根据bean名字获取beanDefinition
                final RootBeanDefinition ex1 = this.getMergedLocalBeanDefinition(beanName);
                this.checkMergedBeanDefinition(ex1, beanName, args);
                //获取当前bean的所有依赖Bean
                String[] dependsOn = ex1.getDependsOn();
                String[] scopeName;
                if(dependsOn != null) {
                    scopeName = dependsOn;
                    int scope = dependsOn.length;

                    for(int ex2 = 0; ex2 < scope; ++ex2) {
                        String dependsOnBean = scopeName[ex2];
                        if(this.isDependent(beanName, dependsOnBean)) {
                            throw new BeanCreationException(ex1.getResourceDescription(), beanName, "Circular depends-on relationship between \'" + beanName + "\' and \'" + dependsOnBean + "\'");
                        }

                        this.registerDependentBean(dependsOnBean, beanName);
                        this.getBean(dependsOnBean);
                    }
                }
                //创建单例bean实例
                if(ex1.isSingleton()) {
                    sharedInstance = this.getSingleton(beanName, new ObjectFactory() {
                        public Object getObject() throws BeansException {
                            try {
                                return AbstractBeanFactory.this.createBean(beanName, ex1, args);
                            } catch (BeansException var2) {
                                AbstractBeanFactory.this.destroySingleton(beanName);
                                throw var2;
                            }
                        }
                    });
                    bean = this.getObjectForBeanInstance(sharedInstance, name, beanName, ex1);
                }
                //创建原型bean 
                else if(ex1.isPrototype()) {
                    scopeName = null;

                    Object var25;
                    try {
                        this.beforePrototypeCreation(beanName);
                        var25 = this.createBean(beanName, ex1, args);
                    } finally {
                        this.afterPrototypeCreation(beanName);
                    }

                    bean = this.getObjectForBeanInstance(var25, name, beanName, ex1);
                } else {
                    String var26 = ex1.getScope();
                    Scope var27 = (Scope)this.scopes.get(var26);
                    if(var27 == null) {
                        throw new IllegalStateException("No Scope registered for scope name \'" + var26 + "\'");
                    }

                    try {
                        Object var28 = var27.get(beanName, new ObjectFactory() {
                            public Object getObject() throws BeansException {
                                AbstractBeanFactory.this.beforePrototypeCreation(beanName);

                                Object var1;
                                try {
                                    var1 = AbstractBeanFactory.this.createBean(beanName, ex1, args);
                                } finally {
                                    AbstractBeanFactory.this.afterPrototypeCreation(beanName);
                                }

                                return var1;
                            }
                        });
                        bean = this.getObjectForBeanInstance(var28, name, beanName, ex1);
                    } catch (IllegalStateException var21) {
                        throw new BeanCreationException(beanName, "Scope \'" + var26 + "\' is not active for the current thread; consider " + "defining a scoped proxy for this bean if you intend to refer to it from a singleton", var21);
                    }
                }
            } catch (BeansException var23) {
                this.cleanupAfterBeanCreationFailure(beanName);
                throw var23;
            }
        }

        if(requiredType != null && bean != null && !requiredType.isAssignableFrom(bean.getClass())) {
            try {
                return this.getTypeConverter().convertIfNecessary(bean, requiredType);
            } catch (TypeMismatchException var22) {
                if(this.logger.isDebugEnabled()) {
                    this.logger.debug("Failed to convert bean \'" + name + "\' to required type [" + ClassUtils.getQualifiedName(requiredType) + "]", var22);
                }

                throw new BeanNotOfRequiredTypeException(name, requiredType, bean.getClass());
            }
        } else {
            return bean;
        }
    }
```


```java
//判断依赖
 private boolean isDependent(String beanName, String dependentBeanName, Set<String> alreadySeen) {
        String canonicalName = this.canonicalName(beanName);
        if(alreadySeen != null && ((Set)alreadySeen).contains(beanName)) {
            return false;
        } else {
            Set dependentBeans = (Set)this.dependentBeanMap.get(canonicalName);
            if(dependentBeans == null) {
                return false;
            } else if(dependentBeans.contains(dependentBeanName)) {
                return true;
            } else {
                Iterator var6 = dependentBeans.iterator();

                String transitiveDependency;
                do {
                    if(!var6.hasNext()) {
                        return false;
                    }

                    transitiveDependency = (String)var6.next();
                    if(alreadySeen == null) {
                        alreadySeen = new HashSet();
                    }

                    ((Set)alreadySeen).add(beanName);
                } while(!this.isDependent(transitiveDependency, dependentBeanName, (Set)alreadySeen));

                return true;
            }
        }
    }
```