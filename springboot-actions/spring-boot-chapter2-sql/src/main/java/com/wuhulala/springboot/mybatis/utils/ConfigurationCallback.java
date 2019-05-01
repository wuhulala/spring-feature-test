package com.wuhulala.springboot.mybatis.utils;

import org.apache.ibatis.session.Configuration;

/**
 * Mybatis 配置 回调函数
 *
 * @author wuhulala 2019/5/1 Create 1.0  <br>
 * @version 1.0
 */
@FunctionalInterface
public interface ConfigurationCallback {

    /**
     * 处理
     *
     * @param configuration 配置
     */
    void process(Configuration configuration);
}
