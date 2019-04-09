package com.wuhulala.spring.batch.config.partition;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * 本地多线程配置
 *
 * @author wuhulala<br>
 * @date 2018/10/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
//@Configuration
public class LocalPartitionBatchConfiguration {


    public static final int GRID_SIZE = 2;

    @Autowired
    public JobExplorer jobExplorer;

    @Bean
    public PartitionHandler taskPartitionHandler(TaskExecutor taskExecutor, @Qualifier("slaveStep") Step slaveStep ){
        TaskExecutorPartitionHandler partitionHandler = new TaskExecutorPartitionHandler();
        partitionHandler.setGridSize(2);
        partitionHandler.setStep(slaveStep);
        partitionHandler.setTaskExecutor(taskExecutor);
        return partitionHandler;
    }

    @Bean
    public TaskExecutor taskExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setThreadNamePrefix("partitionHandler");
        return executor;
    }

}
