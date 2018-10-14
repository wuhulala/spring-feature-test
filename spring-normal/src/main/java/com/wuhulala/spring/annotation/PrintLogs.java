package com.wuhulala.spring.annotation;

import java.lang.annotation.*;

/**
 * @author Wuhulala
 * @version 1.0
 * @updateTime 2016/10/28
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface PrintLogs {
}
