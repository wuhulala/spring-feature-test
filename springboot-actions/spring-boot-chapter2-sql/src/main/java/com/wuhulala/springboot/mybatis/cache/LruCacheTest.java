package com.wuhulala.springboot.mybatis.cache;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能
 *
 * @author xueah20964 2019/5/2 Create 1.0  <br>
 * @version 1.0
 */
public class LruCacheTest {

    public static void main(String[] args) {

        // LinkedHashMap 每一次都会把最新进来的元素放入队尾
        final int size = 3;

        LinkedHashMap<Object, Object> keyMap = new LinkedHashMap<Object, Object>(size, .75F, true) {
            private static final long serialVersionUID = 4267176411845948333L;

            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean tooBig = size() > size;
                return tooBig;
            }
        };

        keyMap.put("1", "2");
        System.out.println(keyMap);
        keyMap.put("2", "3");
        System.out.println(keyMap);
        keyMap.put("1", "2");
        System.out.println(keyMap);
        keyMap.put("5", "6");
        System.out.println(keyMap);
        keyMap.put("2", "3");
        System.out.println(keyMap);
        keyMap.put("3", "4");
        System.out.println(keyMap);
        keyMap.put("4", "3");
        System.out.println(keyMap);

    }

}
