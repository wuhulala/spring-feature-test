#步骤
|方法|说明|调用类方法|所属类|实现类
|----|----|---|----|----|
|refresh()|   更新配置  | ClassPathXmlApplicationContext.ClassPathXmlApplicationContext()|AbstractApplicationContext|无
|obtainFreshBeanFactory()| 获取更新的BeanFactory|ClassPathXmlApplicationContext.refresh()|AbstractApplicationContext|无
|refreshBeanFactory() |更新BeanFactory|AbstractApplicationContext.obtainFreshBeanFactory()|AbstractApplicationContext|AbstractRefreshableApplicationContext
|loadBeanDefinitions()|加载bean|AbstractRefreshableApplicationContext.refreshBeanFactory()|AbstractRefreshableApplicationContext|AbstractXmlApplicationContext
|loadBeanDefinitions()|根据多个resources加载bean|AbstractXmlApplicationContext.loadBeanDefinitions()|AbstractBeanDefinitionReader|无
|loadBeanDefinitions()|根据单个resource加载bean |AbstractBeanDefinitionReader.loadBeanDefinitions()|XmlBeanDefinitionReader|无
|doLoadBeanDefinitions()|加载bean的逻辑核心部分|XmlBeanDefinitionReader.loadBeanDefinitions()|XmlBeanDefinitionReader|无
|registerBeanDefinitions()|注册BeanDefinition|XmlBeanDefinitionReader.doLoadBeanDefinitions()|XmlBeanDefinitionReader|无
|parseBeanDefinitions()|解析BeanDefinition|XmlBeanDefinitionReader.registerBeanDefinitions()|DefaultBeanDefinitionDocumentReader|无
|parseDefaultElement()|分配解析标签的策略 such as bean、alias|DefaultBeanDefinitionDocumentReader.parseBeanDefinitions()|DefaultBeanDefinitionDocumentReader|无
||parseBeanDefinitionElement()|解析具体的标签|DefaultBeanDefinitionDocumentReader.parseDefaultElement()|BeanDefinitionParserDelegate|无
|parseBeanDefinitionAttributes()|注入bean的属性（这里面设置scope、abstract、lazy-init等）|BeanDefinitionParserDelegate.parseBeanDefinitionElement()|BeanDefinitionParserDelegate|无