package com.wuhulala.springboot.mybatis;

import com.wuhulala.springboot.mybatis.model.Country;
import com.wuhulala.springboot.mybatis.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

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

        DefaultSqlSessionFactory sqlSessionFactory = MybatisUtils.getDefaultSqlSessionFactory();

        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<Country> countries = session.selectList("com.wuhulala.springboot.mybatis.mapper.CountryMapper.selectAll");
            System.out.println(countries);
        }

    }
}
