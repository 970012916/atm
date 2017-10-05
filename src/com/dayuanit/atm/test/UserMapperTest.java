package com.dayuanit.atm.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.mapper.UserMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class UserMapperTest {
	
	@Autowired
	private UserMapper userMapper;
	
	private User user;
	
	@Before
	public void init() {
		user = new User();
		user.setPassword("111111");
		user.setUsername("dayuanit");
	}
	
	@Test
	@Rollback
	public void testAdd() {
		int rows = userMapper.add(user);
		assertEquals(1, rows);
	}
	
	@Test
	@Rollback
	public void testGetUserByUserName() {
		userMapper.add(user);
		User queryUser = userMapper.getUserByUserName(user.getUsername());
		assertEquals(user.getId(), queryUser.getId());
	}
}
