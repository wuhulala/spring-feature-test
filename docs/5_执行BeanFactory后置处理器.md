1. 执行BeanDefinitionRegistryPostProcessor接口的后置处理器

1.1 执行硬编码的，就是之前几个步骤直接通过，beanFactory设置的

1.2 执行通过配置注册的处理器，执行顺序是PriorityOrdered（排序是按照从小到大排）->Ordered->普通的

2. 执行普通的BeanFactoryPostProcessor

2.1 执行硬编码的后置处理器

2.2 执行通过配置注册的处理器，执行顺序是PriorityOrdered（排序是按照从小到大排）->Ordered->普通的