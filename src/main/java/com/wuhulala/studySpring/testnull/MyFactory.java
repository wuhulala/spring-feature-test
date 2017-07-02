package com.wuhulala.studySpring.testnull;

import org.springframework.stereotype.Component;

/**
 * Created by xueah20964 on 2017/4/27.
 */
@Component
public class MyFactory {
    // Worker worker;

    //@Autowired
    public MyFactory() {
        //Assert.notNull(worker, "worker must not be null!");
        //this.worker = worker;
    }

    public void createProduct(){
        System.out.println("生产中....");
        //worker.doWork();
        System.out.println("生产完成...");
    }
}
