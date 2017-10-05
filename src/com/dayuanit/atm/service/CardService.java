package com.dayuanit.atm.service;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.util.PageUtils;

public interface CardService {
	
	void openAccount(int userId, int amount);
	
	PageUtils<BankCard> listBankCard(int userId, int currentPageNum);

}
