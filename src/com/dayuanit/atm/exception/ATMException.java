package com.dayuanit.atm.exception;

public class ATMException extends RuntimeException {
	
	private static final long serialVersionUID = -5318147349183093531L;

	public ATMException() {
		
	}
	
	public ATMException(String msg) {
		super(msg);
	}

}
