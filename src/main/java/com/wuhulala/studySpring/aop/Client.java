package com.wuhulala.studySpring.aop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/10/28
 */
@Component
public class Client {
    @Autowired
    private AService aService;


}
