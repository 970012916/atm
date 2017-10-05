package com.dayuanit.atm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.exception.ATMException;
import com.dayuanit.atm.mapper.UserMapper;
import com.dayuanit.atm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	public UserServiceImpl() {
		System.out.println("-----------UserServiceImpl-------------");
	}

	@Override
	public void regist(String username, String password, String confimPassword) {
		
		User existUser = userMapper.getUserByUserName(username);
		if (null != existUser) {
			throw new ATMException("用户已存在");
		}
		
		if (!password.equals(confimPassword)) {
			throw new ATMException("两次密码不相等");
		}
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		
		int rows = userMapper.add(user);
		
		if (1 != rows) {
			throw new ATMException("注册失败");
		}
	}

	@Override
	public User login(String username, String password) {
		User user = userMapper.getUserByUserName(username);
		if (null == user) {
			throw new ATMException("用户名不存在或者密码错误");
		}
		
		if (!user.getPassword().equals(password)) {
			throw new ATMException("用户名不存在或者密码错误");
		}
		
		return user;
	}

}
