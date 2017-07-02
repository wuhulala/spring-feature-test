package com.wuhulala.studySpring;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author xueaohui
 */
@Lazy
@Component
public class HelloImpl implements Hello {
    public void say(String name) {
        System.out.println("Hello " + name);
    }

    public String getString() {
        return "String";
    }
}
