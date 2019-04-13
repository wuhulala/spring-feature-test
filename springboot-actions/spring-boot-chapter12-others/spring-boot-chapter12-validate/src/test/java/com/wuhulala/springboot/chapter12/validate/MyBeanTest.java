package com.wuhulala.springboot.chapter12.validate;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/4/13<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyBeanTest {
    @Autowired
    private MyBean myBean;

    @org.junit.Test(expected = ConstraintViolationException.class)
    public void findByCode() {
        //myBean.findByCode("hasdsd");
        try {

            myBean.findByCode("hasdkasd");
        } catch (ConstraintViolationException e) {
            e.printStackTrace();
        }
        myBean.findByCode("hasdh21asd");
        myBean.findByCode("hasdhk21a22sd");
    }

    @org.junit.Test
    public void testNormalFindByCode() {
        //myBean.findByCode("hasdsd");
        myBean.findByCode("12345678");
    }
}