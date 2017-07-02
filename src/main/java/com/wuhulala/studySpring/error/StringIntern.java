package com.wuhulala.studySpring.error;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/13
 */
public class StringIntern {
    public static void main(String[] args) {
        int a[] = new int[100];
        int b[] = new int [101] ;

        for(int i = 0 ; i  < 99; i ++){
            b[i] = i + 1;
        }

        b[99] = 2;

        /*for(int i = 0 ; i < 100 ; i ++){
            a[b[i]] ++;
            if(a[b[i]] == 2){
                System.out.println(b[i]);
                break;
            }
        }*/

    }
}
