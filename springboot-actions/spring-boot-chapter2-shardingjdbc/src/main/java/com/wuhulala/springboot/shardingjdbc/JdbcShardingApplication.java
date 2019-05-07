package com.wuhulala.springboot.shardingjdbc;

import io.shardingjdbc.core.api.MasterSlaveDataSourceFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 入口
 *
 * @author wuhulala
 */
@SpringBootApplication
public class JdbcShardingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcShardingApplication.class, args);
    }

    /**
     * 配置读写分离数据源
     *
     */
    @Bean
    public DataSource dataSource() throws FileNotFoundException, SQLException, IOException {
        return MasterSlaveDataSourceFactory.createDataSource(ResourceUtils.getFile("classpath:sharding-jdbc.yml"));
    }
}
