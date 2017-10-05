package com.dayuanit.atm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.dayuanit.atm.domain.BankCard;

public interface CardMapper {
	
	int add(BankCard bankCard);
	
	List<BankCard> listBankCardByUserId(@Param("userId") Integer userId, @Param("offset") Integer offset, @Param("pageNum") Integer pageNum);

	int countBankCardByUserId(@Param("userId") Integer userId);
}
