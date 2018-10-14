package com.wuhulala.spring.proxy.jdkspring;

import com.wuhulala.api.User;
import com.wuhulala.api.service.UserService;
import org.springframework.stereotype.Service;

/**
 * 功能说明: ${END}<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
@Service
public class MySpringUserServiceImpl implements UserService {


    ///////////////////////////// 方法区 ////////////////////////////////////


    @Override
    public User createUser(String name) {
        User user = new User();
        user.setName("spring_" + name);
        user.setAge(21);
        System.out.println(user);
        return user;
    }
}
