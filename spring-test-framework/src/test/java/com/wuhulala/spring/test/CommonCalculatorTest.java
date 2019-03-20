package com.wuhulala.spring.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/3/20<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class CommonCalculatorTest {

    private Calculator calculator;

    @Before
    public void init(){
        calculator = new Calculator();
    }

    /**
     * 普通测试
     */
    @Test
    public void add() {
        System.out.println(calculator);
        Assert.assertEquals(3, calculator.add(1, 2));
        Assert.assertEquals(-3, calculator.add(-1, -2));
    }

    /**
     * 异常测试
     */
    @Test(expected = CalculateException.class)
    public void testAddOutOfBound() {
        System.out.println(calculator);
        Assert.assertEquals(-2, calculator.add(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    /**
     * 判断1ms可以运算到多少
     */
    @Test(timeout = 1)
    public void testAddPerformance() {
        for (int i = 0; i < 100; i++) {
            calculator.add(i, i + 1);
        }
    }

}