package com.wuhulala.spring.batch.config.partition;

import com.wuhulala.spring.batch.config.simple.SimpleItemProcessor;
import com.wuhulala.spring.batch.config.simple.SimpleItemWriter;
import com.wuhulala.spring.batch.listener.batch.MyJobListener;
import com.wuhulala.spring.batch.model.Person;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;

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
public class CommonBatchConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CommonBatchConfiguration.class);

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    //----------------------------------------------------------------
    //  基本配置
    //----------------------------------------------------------------
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
    public JobRepository jobRepository(DataSource dataSource,
                                       PlatformTransactionManager transactionManager) throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(transactionManager);
        jobRepositoryFactoryBean.setDatabaseType("oracle");
        return jobRepositoryFactoryBean.getObject();
    }

    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource,
                                         PlatformTransactionManager transactionManager) throws Exception {
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    //----------------------------------------------------------------
    //  监听器配置
    //----------------------------------------------------------------
    @Bean
    public JobExecutionListener myJobListener() {
        return new MyJobListener();
    }


    //----------------------------------------------------------------
    //  reader/writer/processor 三剑客配置
    //----------------------------------------------------------------
    /**
     *  @see StepScope 使用了这个才可以使用上下文中的内容
     */
    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader(@Value("#{stepExecutionContext['input.file.path']}")String filePath) {

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();

        reader.setResource(new ClassPathResource(filePath));

        reader.setLineMapper((s, i) -> {
            Person person = new Person();
            String[] tmp = s.split(",");
            person.setName(tmp[0]);
            person.setAge(tmp[1]);
            person.setCreateTime(sdf.parse(tmp[2]));
            logger.debug(">>>>>>>>> 读取到数据 {}", person);
            return person;
        });
        return reader;
    }


    @Bean
    public ItemWriter<Person> writer() {
        return new SimpleItemWriter();
    }


    @Bean
    public ItemProcessor<Person, Person> processor() {
        return new SimpleItemProcessor();
    }


}
