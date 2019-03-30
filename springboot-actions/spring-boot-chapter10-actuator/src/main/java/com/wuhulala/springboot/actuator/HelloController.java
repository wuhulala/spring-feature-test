package com.wuhulala.springboot.actuator;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * hello controller
 *
 * @author wuhulala<br>
 * @date 2019/3/30<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@RestController
public class HelloController {

    @RequestMapping("/hello/{name}")
    public String sayHello(@PathVariable String name) {
        return "Hello " + name;
    }

}
