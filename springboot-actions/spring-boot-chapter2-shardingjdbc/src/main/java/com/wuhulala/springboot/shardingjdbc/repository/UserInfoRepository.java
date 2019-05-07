package com.wuhulala.springboot.shardingjdbc.repository;

import com.wuhulala.springboot.shardingjdbc.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 用户表操作接口
 *
 * @author wuhulala
 */
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

}
