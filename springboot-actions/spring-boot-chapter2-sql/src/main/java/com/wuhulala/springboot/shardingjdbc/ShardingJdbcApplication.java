package com.wuhulala.springboot.shardingjdbc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 *
 * https://blog.csdn.net/l1028386804/article/details/79368021 分库分表实例
 */
@SpringBootApplication
public class ShardingJdbcApplication implements CommandLineRunner {

	@Override
	public void run(String... args) throws Exception {

	}

	public static void main(String[] args) {
		SpringApplication.run(ShardingJdbcApplication.class, args);
	}

}
