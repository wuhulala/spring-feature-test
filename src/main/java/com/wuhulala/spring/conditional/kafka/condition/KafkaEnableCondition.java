package com.wuhulala.spring.conditional.kafka.condition;

import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * 功能说明: kafka开关条件<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/18<br>
 */
public class KafkaEnableCondition implements ConfigurationCondition {
    private static final String KAFKA_ENABLED_CONFIG_NAME = "kafka.enabled";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        //context.getBeanFactory().getBean(PropertySourcesPlaceholderConfigurer.class);
        Environment env = context.getEnvironment();
        return Boolean.parseBoolean(env.getProperty(KAFKA_ENABLED_CONFIG_NAME));
    }

    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.REGISTER_BEAN;
    }
}
