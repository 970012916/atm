package com.dayuanit.atm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.atm.domain.BankCard;
import com.dayuanit.atm.dto.AjaxResultDTO;
import com.dayuanit.atm.exception.ATMException;
import com.dayuanit.atm.service.CardService;
import com.dayuanit.atm.util.PageUtils;

@Controller
@RequestMapping("/bank")
public class BankCardController extends BaseController {
	
	@Autowired
	private CardService cardService;
	
	@RequestMapping("/openAccount")
	@ResponseBody
	public AjaxResultDTO openAccount(@RequestParam(required=false, defaultValue="0") Integer amount, HttpServletRequest req) {
		try {
			cardService.openAccount(getUserId(req), amount);
		} catch(ATMException ae) {
			return AjaxResultDTO.failed(ae.getMessage());
		}
		
		return AjaxResultDTO.success();
	}
	
	@RequestMapping("/listBankCard")
	@ResponseBody
	public AjaxResultDTO listBankCard(@RequestParam(value="currentPageNum", required=false, defaultValue="1") int currentPageNum, HttpServletRequest req) {
		
		PageUtils<BankCard> pageUtils = null;
		try {
			pageUtils = cardService.listBankCard(getUserId(req), currentPageNum);
		} catch(ATMException ae) {
			return AjaxResultDTO.failed(ae.getMessage());
		}
		
		return AjaxResultDTO.success(pageUtils);
	}
	
	
}
