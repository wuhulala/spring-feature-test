package com.wuhulala.spring.web.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 0_0 o^o
 *
 * @author wuhulala<br>
 * @date 2019/2/20<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@ControllerAdvice
public class GlobalModelAttributeAdvice {

    @ModelAttribute
    public void bindModelAttribute(Model model){
        System.out.println(">>>>>>>>>>>>>>>enter global modelAttribute method .....");
        model.addAttribute("operName", "wuhulala--golbal");
        model.addAttribute("globalFlag", "golbal");
    }


    @ModelAttribute
    public void bindModelAttribute2(Model model){
        System.out.println(">>>>>>>>>>>>>>>enter global modelAttribute method2 .....");
        model.addAttribute("operName", "wuhulala--golbal2");
        model.addAttribute("globalFlag2", "第二个全局方法");
    }

}
