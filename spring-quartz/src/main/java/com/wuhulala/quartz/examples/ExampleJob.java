package com.wuhulala.quartz.examples;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 功能
 *
 * @author xueah20964 2019/1/11 Create 1.0  <br>
 * @version 1.0
 */
public class ExampleJob {

    public void print(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(">>>>>>>>>>>>>>>你好" + name + ",当前时间是：：：" + sdf.format(new Date()));
    }

}
