package com.wuhulala.spring.taskexecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能
 *
 * @author xueah20964 2018/10/19 Create 1.0  <br>
 * @version 1.0
 */
@Configuration
public class TaskExecutorConfig {

    @Bean
    public ThreadPoolTaskScheduler messageBrokerTaskScheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.setThreadNamePrefix("MessageBroker-");
        scheduler.setPoolSize(Runtime.getRuntime().availableProcessors());
        scheduler.setRemoveOnCancelPolicy(true);
        return scheduler;
    }
}
