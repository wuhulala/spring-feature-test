package com.wuhulala.spring.web.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WebBinder测试
 *
 * @author wuhulala<br>
 * @date 2019/2/19<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Controller
public class WebBinderTestController {

    // 个性化 initBinder
    // value 表示只绑定表单的固定key
    @InitBinder("date")
    protected void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    // http://localhost:8080/?date=20180819&date2=2018-09-19
    @RequestMapping("/")
    @ResponseBody
    private String hello(Date date, Date date2){
        System.out.println(date);
        return "hello";
    }

}
