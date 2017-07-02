package com.wuhulala.studySpring.testnull;

import com.wuhulala.studySpring.annotation.TestConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;
import org.springframework.stereotype.Component;
import org.springframework.util.PropertyPlaceholderHelper;
import org.springframework.util.SystemPropertyUtils;

import java.lang.reflect.Field;
import java.util.Properties;


/**
 * 测试自定义注解配置文件
 * <p>
 * Created by xueah20964 on 2017/4/27.
 */
@Component
public class ConfigResourceBeanFactoryPostProcessor implements BeanFactoryPostProcessor,Ordered {
    private static final Logger logger = LoggerFactory.getLogger(ConfigResourceBeanFactoryPostProcessor.class);
    private static Properties allProperties;
    private static final PropertyPlaceholderHelper PROPERTY_PLACEHOLDER_HELPER = new PropertyPlaceholderHelper(
            SystemPropertyUtils.PLACEHOLDER_PREFIX,
            SystemPropertyUtils.PLACEHOLDER_SUFFIX);


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        PropertySourcesPlaceholderConfigurer pspc = beanFactory.getBean(PropertySourcesPlaceholderConfigurer.class);
        init(pspc);
        Class<?> workerClazz = Worker.class;

        String[] beanNames = beanFactory.getBeanNamesForType(workerClazz);
        logger.debug("-----------------config worker start------------------");

        for (String name : beanNames) {
            Worker worker = (Worker) beanFactory.getBean(name);
            Field[] fields = workerClazz.getDeclaredFields();
            System.out.println("bean[" + name + "]");
            for (Field field : fields) {
                //System.out.printf("---field[" + field.getName() + "]:");
                if (field.isAnnotationPresent(TestConfig.class)) {
                    TestConfig testConfig = field.getAnnotation(TestConfig.class);
                    String key = testConfig.value();
                    Object value = resolveValueWithKey(key);
                   // System.out.println(value);
                    field.setAccessible(true);

                    try {
                        field.set(worker, value);
                    } catch (IllegalAccessException e) {
                        logger.error("写入属性失败", e);
                        e.printStackTrace();
                    }
                }
            }
        }


        logger.debug("-----------------config worker end------------------");
    }

    private Object resolveValueWithKey(String key) {
        return PROPERTY_PLACEHOLDER_HELPER.replacePlaceholders(key,allProperties);
    }


    private void init(PropertySourcesPlaceholderConfigurer pspc) {
        PropertySources pp = pspc.getAppliedPropertySources();
        //environmentProperties 系统变量
        //localProperties 自定义变量
        PropertySource<Properties> ps = (PropertySource<Properties>) pp.get("localProperties");
        allProperties = ps.getSource();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
