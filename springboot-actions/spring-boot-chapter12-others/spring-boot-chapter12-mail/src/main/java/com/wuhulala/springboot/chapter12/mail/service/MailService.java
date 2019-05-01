package com.wuhulala.springboot.chapter12.mail.service;

/**
 * 邮件服务
 *
 * @author wuhulala<br>
 * @date 2019/4/13<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
public interface MailService {

    /**
     * 发送邮件
     *
     * @param address 目标地址
     * @param content 内容
     */
    void send(String address, String content);
}
