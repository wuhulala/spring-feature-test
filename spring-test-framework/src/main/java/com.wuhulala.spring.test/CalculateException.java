package com.wuhulala.spring.test;

/**
 * 计算异常
 *
 * @author wuhulala<br>
 * @date 2019/3/20<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public class CalculateException extends RuntimeException{

    public CalculateException() {
        super();
    }

    public CalculateException(String message) {
        super(message);
    }

    public CalculateException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculateException(Throwable cause) {
        super(cause);
    }

    protected CalculateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
