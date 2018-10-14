package com.wuhulala.spring.testnull;

import com.wuhulala.spring.annotation.TestConfig;

/**
 * Created by xueah20964 on 2017/4/27.
 */
public class Worker {
    @TestConfig("${asd.asdasd}")
    private String name;

    private int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void doWork(){
        System.out.println(name + "---" +age + ":[do Work...]");
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
