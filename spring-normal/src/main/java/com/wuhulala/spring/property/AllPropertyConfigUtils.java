package com.wuhulala.spring.property;

import java.util.Properties;

/**
 * author： wuhulala
 * date： 2017/7/30
 * version: 1.0
 * description: 提前获取所有属性
 */
public class AllPropertyConfigUtils {

    private static Properties props;

    public static Properties getProps() {
        return props;
    }

    public static void addProps(Properties props) {
        AllPropertyConfigUtils.props.putAll(props);
    }
}
