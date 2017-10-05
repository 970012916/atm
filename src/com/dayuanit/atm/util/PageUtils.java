package com.dayuanit.atm.util;

import java.util.List;

public class PageUtils<T> {
	
	public static final int PRE_PAGE_NUM = 2;
	
	private int totalPageNum;
	private int currentPageNum;
//	private Object data;
	private List<T> data;
	
//	public void setData(Object data) {
//		this.data = data;
//	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

//	public Object getData() {
//		return data;
//	}

	public PageUtils(int currentPageNum, int totalDataNum) {
		this.currentPageNum = currentPageNum;
		this.totalPageNum = getTotalPageNum(totalDataNum);
	}
	
//	public static PageUtils build(int currentPageNum, int totalDataNum) {
//		return new PageUtils<T>(currentPageNum, getTotalPageNum(totalDataNum));
//	}
	
	private static int getTotalPageNum(int totalDataNum) {
		return (totalDataNum % PRE_PAGE_NUM) == 0 ? totalDataNum / PRE_PAGE_NUM : (totalDataNum / PRE_PAGE_NUM) + 1;
	}
	
	public int getOffset() {
		return (currentPageNum - 1) * PRE_PAGE_NUM;
	}

}
