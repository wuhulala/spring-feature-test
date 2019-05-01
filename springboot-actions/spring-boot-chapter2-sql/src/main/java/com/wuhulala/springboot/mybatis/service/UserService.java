package com.wuhulala.springboot.mybatis.service;


import com.wuhulala.springboot.mybatis.model.SysUser;

import java.util.List;

public interface UserService {
	
	/**
	 * 通过 id 查询用户
	 * 
	 * @param id
	 * @return
	 */
	SysUser findById(Long id);
	
	/**
	 * 查询全部用户
	 * 
	 * @return
	 */
	List<SysUser> findAll();
}
