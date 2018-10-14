package com.wuhulala.spring.batch.config;

import com.wuhulala.spring.batch.config.simple.SimpleItemProcessor;
import com.wuhulala.spring.batch.config.simple.SimpleItemWriter;
import com.wuhulala.spring.batch.listener.batch.MyJobListener;
import com.wuhulala.spring.batch.model.Person;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * 功能
 * // TODO https://docs.spring.io/spring-batch/3.0.x/reference/html/scalability.html 并行
 * // TODO https://docs.spring.io/spring-batch/3.0.x/reference/html/repeat.html 重复
 * // TODO https://docs.spring.io/spring-batch/3.0.x/reference/html/retry.html 重试
 *
 * @author xueah20964 2018/10/14 Create 1.0  <br>
 * @version 1.0
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {

    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("oracle.jdbc.OracleDriver");
        dataSource.setUsername("hs_sys");
        dataSource.setPassword("Hundsun123");
        dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(10);
        dataSource.setMaxWait(10000);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQuery("select 1 from dual");
        return dataSource;
    }

    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("oracle");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1) {
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)
                .end()
                .listener(myJobListener())
                .build();
    }

    @Bean
    public JobExecutionListener myJobListener() {
        return new MyJobListener();
    }

    @Bean
    public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Person> reader, ItemWriter<Person> writer, ItemProcessor<Person, Person> processor) {
        return stepBuilderFactory
                .get("step1")
                .<Person, Person>chunk(65000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @StepScope // 必须和实现类同时使用，即返回值必须是实现类
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['input.file.path']}")String filePath) {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        //ItemReader<Person>
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(filePath));
        reader.setLineMapper((s, i) -> {
            String[] ss = s.split(",");
            Person person = new Person();
            person.setName(ss[0]);
            person.setAge(ss[1]);
            person.setCreateTime(sdf.parse(ss[2]));
            return person;
        });
        return reader;
    }


    @Bean
    public ItemWriter<Person> writer() {
        //ItemReader<Person>

        return new SimpleItemWriter();
    }


    @Bean
    public ItemProcessor<Person, Person> processor() {
        //ItemReader<Person>
        ItemProcessor<Person, Person> processor = new SimpleItemProcessor();

        return processor;
    }
}