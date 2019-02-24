package com.wuhulala.spring.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *  ModelAttribute 测试
 *
 * @author wuhulala<br>
 * @date 2019/2/24<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Controller
public class ModelAttributeController {

    @ModelAttribute
    public void bindModelAttribute(Model model){
        System.out.println(">>>>>>>>>>>>>>>enter modelAttribute method .....");
        // 会覆盖全局
        model.addAttribute("operName", "wuhulala");
    }

    /**
     * 返回值绑定到model上，这里可以做一些通用的参数的查询，如配置参数、字典项等
     * @param model
     * @return
     */
    @ModelAttribute("hello")
    public String returnModelAttribute(Model model){
        System.out.println(">>>>>>>>>>>>>>>enter modelAttribute return method .....");
        // 会覆盖全局
        return "hello";
    }


    @RequestMapping("/modelAttribute")
    @ResponseBody
    public String index(Model model){
        System.out.println(model.asMap());
        return "modelAttribute";
    }

//    >>>>>>>>>>>>>>>enter global modelAttribute method .....
//    >>>>>>>>>>>>>>>enter global modelAttribute method2 .....
//    >>>>>>>>>>>>>>>enter modelAttribute method .....
//    {operName=wuhulala, globalFlag=golbal, globalFlag2=第二个全局方法}
}
