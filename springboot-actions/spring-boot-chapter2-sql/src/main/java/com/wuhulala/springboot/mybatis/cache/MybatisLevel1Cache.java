package com.wuhulala.springboot.mybatis.cache;

import com.wuhulala.springboot.mybatis.model.Country;
import com.wuhulala.springboot.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.util.List;

/**
 * 一级测试
 *
 * @author xueah20964 2019/5/1 Create 1.0  <br>
 * @version 1.0
 */
public class MybatisLevel1Cache {

    public static void main(String[] args) throws IOException {
        // 一级缓存作用域 默认
        // configuration.setLocalCacheScope(LocalCacheScope.SESSION);

        DefaultSqlSessionFactory sqlSessionFactory = MybatisUtils.getDefaultSqlSessionFactory(configuration -> {
            // 设置一级缓存作用域为STATEMENT
            // configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
        });


        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Country> countries = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
            System.out.println(">>>>>>>> 第一次查询..." + countries);
            countries = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
            System.out.println(">>>>>>>> 第二次查询..." + countries);
            // 会清空缓存
            session.commit();
            countries = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
            System.out.println(">>>>>>>> 第三次查询..." + countries);
            System.out.println(countries);
        }

    }

}
