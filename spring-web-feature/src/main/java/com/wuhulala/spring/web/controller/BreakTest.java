package com.wuhulala.spring.web.controller;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/2/27<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class BreakTest {

    public static void main(String[] args) {
        System.out.println(">>>>>>>>>>>>>>>>>>start<<<<<<<<<<<<<<<<<<<<<");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            if (i == 3){
                break; // 跳出for 循环
            }
        }
        System.out.println(">>>>>>>>>>>>>>>>>>end<<<<<<<<<<<<<<<<<<<<<");
    }


}
