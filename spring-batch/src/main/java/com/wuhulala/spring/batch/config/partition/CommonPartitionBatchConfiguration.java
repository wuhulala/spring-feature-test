package com.wuhulala.spring.batch.config.partition;

import com.wuhulala.spring.batch.config.partition.aggregator.MyStepExecutionAggregator;
import com.wuhulala.spring.batch.listener.batch.MyJobListener;
import com.wuhulala.spring.batch.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 公共分区类型的并行调用配置
 *
 * @author wuhulala<br>
 * @date 2018/10/15<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Configuration
public class CommonPartitionBatchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CommonPartitionBatchConfiguration.class);

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    //----------------------------------------------------------------
    //  partitionJob 配置
    //----------------------------------------------------------------
    @Bean
    public Job partitionJob(JobBuilderFactory jobs,
                            @Qualifier("masterStep") Step masterStep,
                            @Qualifier("slaveStep") Step slaveStep) {
        return jobs.get("partitionJob")
                .start(masterStep)
                .incrementer(new RunIdIncrementer())
                .listener(new MyJobListener())
                .build();
    }


    @Bean(name = "masterStep")
    public Step masterStep(@Qualifier("slaveStep") Step slaveStep,
                           PartitionHandler partitionHandler) {
        return stepBuilderFactory.get("masterStep")
                // 配置一个PartitionStep
                .partitioner(slaveStep.getName(), new FilePartitioner())
                .step(slaveStep)
                .partitionHandler(partitionHandler)
                .aggregator(stepExecutionAggregator())
                .build();
    }

    @Bean(name = "slaveStep")
    public Step slaveStep(ItemReader<Person> reader, ItemWriter<Person> writer, ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory.get("slaveStep")
                .<Person, Person>chunk(5)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public MyStepExecutionAggregator stepExecutionAggregator(){
        return new MyStepExecutionAggregator();
    }

}
