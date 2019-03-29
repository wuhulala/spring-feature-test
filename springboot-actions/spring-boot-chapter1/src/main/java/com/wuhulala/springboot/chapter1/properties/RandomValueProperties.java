package com.wuhulala.springboot.chapter1.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 随机数测试
 *
 * @author wuhulala<br>
 * @date 2019/3/28<br>
 * @description o_o<br>
 * @since v1.0<br>
 */
@Component
@ConfigurationProperties("my")
public class RandomValueProperties {
    private String secret;
    private int number;
    private long bigNumber;
    private String uuid;
    private int numberlessThanTen;
    private int numberInRange;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getBigNumber() {
        return bigNumber;
    }

    public void setBigNumber(long bigNumber) {
        this.bigNumber = bigNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getNumberlessThanTen() {
        return numberlessThanTen;
    }

    public void setNumberlessThanTen(int numberlessThanTen) {
        this.numberlessThanTen = numberlessThanTen;
    }

    public int getNumberInRange() {
        return numberInRange;
    }

    public void setNumberInRange(int numberInRange) {
        this.numberInRange = numberInRange;
    }

    @Override
    public String toString() {
        return "RandomValueProperties{" +
                "secret='" + secret + '\'' +
                ", number=" + number +
                ", bigNumber=" + bigNumber +
                ", uuid='" + uuid + '\'' +
                ", numberlessThanTen=" + numberlessThanTen +
                ", numberInRange=" + numberInRange +
                '}';
    }
}
