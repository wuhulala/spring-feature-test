package com.wuhulala.studySpring.error;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/11/13
 */
public class HeapOOM {

    static class OOMObject{}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();

        for (int i = 0; i < 100000; i++) {
            list.add(new OOMObject());
        }


        System.out.println("new end");

        while(true){
            list.add(new OOMObject());
        }

    }
}
