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

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.mapper.CardMapper;

@ContextConfiguration("/spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CardMapperTest {
	
	@Autowired
	private CardMapper cardMapper;
	
	private BankCard bankCard;
	
	@Before
	public void init() {
		bankCard = new BankCard();
		bankCard.setBalance(100);
		bankCard.setCardNum("1000");
		bankCard.setStatus(1);
		bankCard.setUserId(1000);
	}
	
	@Test
	@Rollback
	public void testAdd() {
		int rows = cardMapper.add(bankCard);
		assertEquals(1, rows);
	}
	
}
