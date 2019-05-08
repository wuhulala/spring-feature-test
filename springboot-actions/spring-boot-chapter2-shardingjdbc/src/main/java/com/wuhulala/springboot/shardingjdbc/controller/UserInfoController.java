package com.wuhulala.springboot.shardingjdbc.controller;

import com.wuhulala.springboot.shardingjdbc.model.UserInfo;
import com.wuhulala.springboot.shardingjdbc.repository.UserInfoRepository;
import com.wuhulala.springboot.shardingjdbc.util.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 用户信息控制器
 *
 * @author wuhulala
 */
@RestController
public class UserInfoController {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoController(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    /**
     * 获取所有用户信息
     *
     */
    @GetMapping("/userinfo")
    public List<UserInfo> getUserInfos() {
        return userInfoRepository.findAll();
    }

    /**
     * 增加新用户
     *
     */
    @GetMapping("/userinfo/{name}")
    public UserInfo addUserInfo(@PathVariable String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setId(SnowflakeIdWorker.getId());
        userInfo.setName(name);
        return userInfoRepository.save(userInfo);
    }

    /**
     * 增加新用户后再立即查找该用户信息
     *
     * @param name
     * @return
     */
    @GetMapping("/userinfo/wr/{name}")
    public UserInfo writeAndRead(@PathVariable String name) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfoRepository.saveAndFlush(userInfo);
        Example<UserInfo> example = Example.of(userInfo);
        return userInfoRepository.findOne(example).get();
    }
}
