package com.wuhulala.springboot.mybatis.service.impl;

import com.wuhulala.springboot.mybatis.mapper.UserMapper;
import com.wuhulala.springboot.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wuhulala.springboot.mybatis.model.SysUser;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public SysUser findById(Long id) {
		return userMapper.selectById(id);
	}
	
	@Override
	public List<SysUser> findAll() {
		return userMapper.selectAll();
	}
	
}
