package com.wuhulala.spring.batch.model;

import lombok.Data;

import java.util.Date;

/**
 * 功能
 *
 * @author xueah20964 2018/10/14 Create 1.0  <br>
 * @version 1.0
 */
@Data
public class Person {

    private String name;

    private String age;

    private String upCaseName;

    private Date createTime;

    private Date updateTime;



}
