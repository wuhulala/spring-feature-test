package com.wuhulala.springboot.devtools;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/5/16<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public String hello(){
        System.out.println("hello ....");
        return "hello";
    }

}
