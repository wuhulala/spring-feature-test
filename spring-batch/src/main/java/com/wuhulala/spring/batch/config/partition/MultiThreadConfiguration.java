package com.wuhulala.spring.batch.config.partition;

import com.wuhulala.spring.batch.model.Person;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

/**
 * 简单的多线程step
 *
 * @author wuhulala<br>
 * @date 2019/4/3<br>
 * @description o_o<br>
 * @link https://docs.spring.io/spring-batch/4.1.x/reference/html/scalability.html#multithreadedStep
 * @since v1.0<br>
 */
//@Configuration
public class MultiThreadConfiguration {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Bean
    public TaskExecutor taskExecutor(){
        return new SimpleAsyncTaskExecutor("spring_batch");
    }

    @Bean
    public Step sampleStep(TaskExecutor taskExecutor, ItemReader<Person> reader, ItemWriter<Person> writer, ItemProcessor<Person, Person> processor) {
        return this.stepBuilderFactory.get("sampleStep")
                .<Person, Person>chunk(10)
                .reader(reader)
                .writer(writer)
                .processor(processor)
                .taskExecutor(taskExecutor)
                .build();
    }
}
