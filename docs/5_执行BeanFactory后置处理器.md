### 1. 执行BeanDefinitionRegistryPostProcessor接口的后置处理器

  1.1 执行硬编码的，就是之前几个步骤直接通过，beanFactory设置的

  1.2 执行通过配置注册的处理器，执行顺序是PriorityOrdered（排序是按照从小到大排）->Ordered->普通的
  
  > tip: condition的判断再次，因为properties还没有加载，所以此时condition拿不到properties，所以根据properties判断的需要手动加载，但是spring-boot上面无此问题，此问题还有待商榷去解决

### 2. 执行普通的BeanFactoryPostProcessor

  2.1 执行硬编码的后置处理器

  2.2 执行通过配置注册的处理器，执行顺序是PriorityOrdered（排序是按照从小到大排）->Ordered->普通的
