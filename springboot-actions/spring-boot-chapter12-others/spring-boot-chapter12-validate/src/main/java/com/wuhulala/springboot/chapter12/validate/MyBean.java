package com.wuhulala.springboot.chapter12.validate;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Service
@Validated
public class MyBean {

    public String findByCode(@Size(min = 8, max = 10, message = "用户编码")
                             @Pattern(regexp = "^\\d{8,10}$", message = "用户编码正则表达式") String code) {
        return "you code is " + code;
    }

}