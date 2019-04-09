package com.wuhulala.springboot.chapter1;

import javafx.scene.Parent;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * spring Boot 入门 第一章节
 *
 * @author wuhulala<br>
 * @date 2019/3/28<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@SpringBootApplication
public class Chapter1Application {

    public static void main(String[] args) {
        SpringApplication.run(Chapter1Application.class, args);
    }


    public static void main2(String[] args) {
        SpringApplication app = new SpringApplication(Chapter1Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    public static void main3(String[] args) {
        new SpringApplicationBuilder()
                .sources(Parent.class)
                .child(Chapter1Application.class)
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

}
