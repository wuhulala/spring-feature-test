### 1. 设置bean的类加载器
### 2. 设置表达式解析器，默认设置的是StandardBeanExpressionResolver
### 3. 添加属性编辑器的注册器
### 4. 添加ApplicationContextAware接口的处理器
### 5. 忽略给定接口的自动装配功能，有EnvironmentAware、EmbeddedValueResolverAware、ResourceLoaderAware、ApplicationEventPublisherAware、MessageSourceAware、ApplicationContextAware等等
   错误的理解【比如A和B两个Bean，如果A依赖于B，那么在A初始化的时候，如果B此时还未初始化，也会把B给初始化，但是如果B实现了以上接口，就不会被初始化。！！！！留作测试用！！！！】，
    真实情况是....只是在装配的时候不会去检查这个Bean存不存在，其它的bean都是需要判断是否存在的，不存在会去初始化Bean。但是这个因为Environment在这个之前都是已经存在于Bean容器中了，并不是不去初始化它

    ```java
    public class EnvironmentAwareBean implements EnvironmentAware{
        @Autowired  
        private Environment environment;
    
        @Autowired
        private BeanFactory beanFactory;
        public EnvironmentAwareBean(){
            System.out.println("-------------------初始化 EnvironmentAwareBean-----------------");
        }
    
        @Override
        public void setEnvironment(Environment environment) {
            System.out.println("-------------------注入 Environment-----------------");
            this.environment = environment;
        }
    }
    ```

### 6. 将一些特定类型的接口和类，注册为bean，可以被依赖，比如ApplicationContext
### 7. 注册早期后处理器，这就是硬编码BeanFactory后处理器，会在第一步执行。用于检测内部bean是否为ApplicationListener。
### 8. 检测是否有LoadTimeWeaver//这块还不太清晰
### 9. 注册属性环境Bean【environment、systemProperties、systemEnvironment】，可以直接通过autowired注入，这是最早的实例化好的三个bean了
