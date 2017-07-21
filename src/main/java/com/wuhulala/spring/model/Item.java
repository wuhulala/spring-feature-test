package com.wuhulala.spring.model;

/**
 * @author xueaohui
 * @version 1.0
 * @date 2017/4/23
 * @link https://github.com/wuhulala
 */
public class Item {
    private Integer id;
    private String name;
    private int number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
