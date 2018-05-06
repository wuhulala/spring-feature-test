package com.wuhulala.spring.primarybean;

import com.wuhulala.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 功能说明: com.wuhulala.spring.primarybean<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: wuhulala<br>
 * 开发时间: 2018/5/2<br>
 */
@Service
public class UserFactory {
    @Autowired
    private User user;

    @Autowired
    @Qualifier("user2")
    private User user2;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public void print(){
        System.out.println("user is " + user.getName());
        System.out.println("user2 is " + user2.getName());
    }
}
