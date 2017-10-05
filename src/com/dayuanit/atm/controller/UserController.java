package com.dayuanit.atm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.atm.domain.User;
import com.dayuanit.atm.dto.AjaxResultDTO;
import com.dayuanit.atm.exception.ATMException;
import com.dayuanit.atm.service.UserService;
import com.dayuanit.atm.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toRegist")
	public String toRegist() {
		return "regist";
	}
	
	@RequestMapping("/toLogin")
	public String toLogin() {
		return "login";
	}
	
	@RequestMapping("/toUsercenter")
	public String toUsercenter() {
		return "usercenter";
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public AjaxResultDTO regist(UserVO userVO) {
		
		try {
			userService.regist(userVO.getUsername(), userVO.getPassword(), userVO.getPasswordOther());
		} catch(ATMException ae) {
			return AjaxResultDTO.failed(ae.getMessage());
		}
		
		return AjaxResultDTO.success();
	}
	
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResultDTO login(String username, String password, HttpServletRequest req) {
		try {
			User user = userService.login(username, password);
			req.getSession().setAttribute(LOGIN_FLAG, user);
		} catch(ATMException ae) {
			return AjaxResultDTO.failed(ae.getMessage());
		}
		
		return AjaxResultDTO.success();
	}
}
