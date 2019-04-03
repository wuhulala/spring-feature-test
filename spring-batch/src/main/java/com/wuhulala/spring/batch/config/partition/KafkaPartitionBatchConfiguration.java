package com.wuhulala.spring.batch.config.partition;

import com.wuhulala.spring.batch.config.partition.kafka.KafkaPartitionHandler;
import com.wuhulala.spring.batch.config.partition.kafka.KafkaStepExecutionRequestHandlerAdapter;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.integration.partition.BeanFactoryStepLocator;
import org.springframework.batch.integration.partition.StepExecutionRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2018/10/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Configuration
@EnableBatchProcessing
public class KafkaPartitionBatchConfiguration {


    public static final int GRID_SIZE = 2;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    public JobExplorer jobExplorer;

    @Bean
    @Autowired
    public PartitionHandler partitionHandler(KafkaTemplate kafkaTemplate) throws Exception {
        KafkaPartitionHandler partitionHandler = new KafkaPartitionHandler();
        partitionHandler.setStepName("slaveStep");
        partitionHandler.setGridSize(GRID_SIZE);
        partitionHandler.setKafkaTemplate(kafkaTemplate);
        partitionHandler.setJobExplorer(jobExplorer);
        partitionHandler.setTopic("hello-batch");
        return partitionHandler;
    }

    @Bean
    public StepExecutionRequestHandler stepExecutionRequestHandler() {
        BeanFactoryStepLocator stepLocator = new BeanFactoryStepLocator();
        stepLocator.setBeanFactory(this.applicationContext);

        StepExecutionRequestHandler handler = new StepExecutionRequestHandler();
        handler.setStepLocator(stepLocator);
        handler.setJobExplorer(jobExplorer);
        return handler;
    }

    @Bean
    public KafkaStepExecutionRequestHandlerAdapter kafkaSeRequestHandlerAdapter(StepExecutionRequestHandler handler){
        return new KafkaStepExecutionRequestHandlerAdapter(handler);
    }

}
