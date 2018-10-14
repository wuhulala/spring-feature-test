package com.wuhulala.spring.proxy;

import com.wuhulala.api.User;
import com.wuhulala.api.service.UserService;

/**
 * 功能说明: 用户服务实现<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
public class UserServiceImpl implements UserService{


    ///////////////////////////// 方法区 ////////////////////////////////////


    @Override
    public User createUser(String name) {
        User user = new User();
        user.setName(name);
        user.setAge(20);
        System.out.println(user);
        return user;
    }
}
