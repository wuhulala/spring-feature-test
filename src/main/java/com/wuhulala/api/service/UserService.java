package com.wuhulala.api.service;

import com.wuhulala.api.User;

/**
 * 功能说明: 用户服务<br>
 * 注意事项: <br>
 * 系统版本: v1.0<br>
 * 开发人员: xueah20964<br>
 * 开发时间: 2018/5/6<br>
 */
public interface UserService {


    ///////////////////////////// 方法区 ////////////////////////////////////

    /**
     * 创建用户
     *
     * @param name 用户名
     * @return 用户
     */
    User createUser(String name);
}
