package com.dayuanit.atm.service.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.exception.ATMException;
import com.dayuanit.atm.mapper.CardMapper;
import com.dayuanit.atm.service.CardService;
import com.dayuanit.atm.util.PageUtils;

@Service
public class CardServiceImpl implements CardService {
	
	@Autowired
	private CardMapper cardMapper;

	@Override
	public void openAccount(int userId, int amount) {
		BankCard bc = new BankCard();
		bc.setCardNum(createBankNum());
		bc.setStatus(1);
		bc.setUserId(userId);
		bc.setBalance(amount);
		
		int rows = cardMapper.add(bc);
		if (1 != rows) {
			throw new ATMException("开户失败");
		}
	}
	
	private String createBankNum() {
		Random random = new Random();
		String cardNum = "";
		for (int i=0; i<5;i++) {
			int num = random.nextInt(10);
			cardNum += String.valueOf(num);
		}
		
		return cardNum;
	}

	@Override
	public PageUtils<BankCard> listBankCard(int userId, int currentPageNum) {
		
		int total = cardMapper.countBankCardByUserId(userId);
		
		PageUtils<BankCard> pageUtils = new PageUtils<BankCard>(currentPageNum, total);
		
		List<BankCard> list = cardMapper.listBankCardByUserId(userId, pageUtils.getOffset(), PageUtils.PRE_PAGE_NUM);
		
		pageUtils.setData(list);
		
		return pageUtils;
	}

}
