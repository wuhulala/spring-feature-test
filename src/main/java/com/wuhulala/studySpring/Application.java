package com.wuhulala.studySpring;

import com.wuhulala.studySpring.annotation.AoHuiAppAnnotation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by xueah20964 on 2017/4/24.
 */
@AoHuiAppAnnotation
public class Application {
    @Value("${aasd}")
    private String a;

    public void print(){
        System.out.println("a ================= " + a);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext(Application.class);
        Application application = (Application) annotationConfigApplicationContext.getBean("application");
        application.print();
    }
}

