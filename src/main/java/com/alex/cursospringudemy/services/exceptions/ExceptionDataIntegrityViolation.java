package com.alex.cursospringudemy.services.exceptions;

public class ExceptionDataIntegrityViolation extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ExceptionDataIntegrityViolation(String msg) {
		super(msg);
	}
	
	public ExceptionDataIntegrityViolation(String msg, Throwable cause) {
		super(msg, cause);
	}

}
