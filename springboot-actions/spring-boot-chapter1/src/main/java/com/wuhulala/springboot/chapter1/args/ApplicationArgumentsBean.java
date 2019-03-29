package com.wuhulala.springboot.chapter1.args;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 获取java -jar 用户自定义的参数
 *
 * @author wuhulala<br>
 * @date 2019/3/28<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Component
public class ApplicationArgumentsBean {

    @Autowired
    public ApplicationArgumentsBean(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        System.out.println(">>>>>>>>>>>>>>>>当前入参:::" + files);
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }


}
