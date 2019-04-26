package com.wuhulala.springboot.mybatis;

import com.wuhulala.springboot.mybatis.mapper.CountryMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 启动类
 */
@SpringBootApplication
@MapperScan(value = { 
		"com.wuhulala.springboot.mybatis.mapper"
	},
	nameGenerator = MapperNameGenerator.class
)
public class Application implements CommandLineRunner {

	@Autowired
	private CountryMapper countryMapper;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		countryMapper.selectAll();
	}

}
