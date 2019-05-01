package com.wuhulala.springboot.mybatis.threadsafe;

import com.wuhulala.springboot.mybatis.model.Country;
import com.wuhulala.springboot.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/4/27<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class MybatisThreadSafeTestApplication {

    public static void main(String[] args) throws IOException, InterruptedException {

        DefaultSqlSessionFactory sqlSessionFactory = MybatisUtils.getDefaultSqlSessionFactory(null);

        // 线程不安全的
        try (SqlSession session = sqlSessionFactory.openSession();) {
            IntStream.iterate(1, n -> n + 1).limit(10).parallel().forEach(i -> {
                List<Country> countries1 = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                List<Country> countries2 = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
                // 有可能不相等
                // 当其它线程查询的时候，数据库已经进行了变化，那么这样第二条线程**更新**了缓存，第二次再查询数据库，其内容已经发生了变化
                System.out.println(countries2.equals(countries1));
            });


        }

        System.out.println("-----------------------------------------------------");
        ThreadSafeSqlSession session = new ThreadSafeSqlSession(sqlSessionFactory);
        try {
            IntStream.iterate(1, n -> n + 1).limit(10).parallel().forEach(i -> {
                List<Country> countries = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
                System.out.println(Thread.currentThread().getName() + ":::" + countries);
            });

        } finally {
            session.close();
        }

    }


}
