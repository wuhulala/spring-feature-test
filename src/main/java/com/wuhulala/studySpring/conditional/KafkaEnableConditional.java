package com.wuhulala.studySpring.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * 功能说明: com.wuhulala.studySpring.conditional<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/18<br>
 */
@Component
public class KafkaEnableConditional implements Condition {
    private static final String KAFKA_ENABLED_CONFIG_NAME = "kafka.enabled";


    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        return Boolean.parseBoolean(env.getProperty(KAFKA_ENABLED_CONFIG_NAME));
    }

}
