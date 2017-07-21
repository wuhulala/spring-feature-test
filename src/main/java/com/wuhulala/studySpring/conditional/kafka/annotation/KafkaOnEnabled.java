package com.wuhulala.studySpring.conditional.kafka.annotation;

import com.wuhulala.studySpring.conditional.kafka.condition.KafkaEnableCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 功能说明: com.hundsun.yht.kernel.config.kafka<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/20<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Conditional(KafkaEnableCondition.class)
public @interface KafkaOnEnabled {
}
