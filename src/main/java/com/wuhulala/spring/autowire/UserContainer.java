package com.wuhulala.spring.autowire;

import com.wuhulala.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 功能说明: com.wuhulala.api<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2017/7/21<br>
 */
@Component
public class UserContainer {
    @Autowired
    private User user1;

    @Resource
    private User user2;

    @Resource
    private User user3;

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public String toString() {
        return "UserContainer{" +
                "user1=" + user1.getName() +
                ", user2=" + user2.getName() +
                ", user3=" + user3.getName() +
                '}';
    }
}
