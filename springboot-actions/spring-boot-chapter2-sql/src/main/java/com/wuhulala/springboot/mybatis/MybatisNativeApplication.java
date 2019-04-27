package com.wuhulala.springboot.mybatis;

import com.wuhulala.springboot.mybatis.mapper.CountryMapper;
import com.wuhulala.springboot.mybatis.model.Country;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/4/27<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class MybatisNativeApplication {

    public static void main(String[] args) throws IOException {

        HikariConfig dsConfig = new HikariConfig();
        dsConfig.setJdbcUrl("jdbc:mysql://mysqlhost:3306/mybatis");
        dsConfig.setUsername("mybatis");
        dsConfig.setPassword("mybatis1234");
        //config.setDriverClassName("com.mysql.jdbc.Driver");

        DataSource dataSource = new HikariDataSource(dsConfig);
        TransactionFactory transactionFactory = new JdbcTransactionFactory();

        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(CountryMapper.class);

        DefaultSqlSessionFactory sqlSessionFactory = (DefaultSqlSessionFactory) new SqlSessionFactoryBuilder().build(configuration);
        PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource("classpath:mapper/CountryMapper.xml");
        XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(resource.getInputStream(), configuration, resource.toString(), configuration.getSqlFragments());
        xmlMapperBuilder.parse();


        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Country> countries = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
            System.out.println(countries);
        }

    }
}
