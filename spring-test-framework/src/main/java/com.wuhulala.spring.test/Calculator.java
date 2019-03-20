package com.wuhulala.spring.test;

/**
 * 计算器
 *
 * @author wuhulala<br>
 * @date 2019/3/20<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class Calculator {

    public int add(int a, int b) {
        if ((a & b) == Integer.MAX_VALUE && (a ^ b) != Integer.MAX_VALUE) {
            throw new CalculateException(String.format("数字[%d + %d]相加超出边界!", a, b));
        }
        //System.out.println(String.format("%d + %d = %d", a, b, a + b));
        return a + b;
    }

}
