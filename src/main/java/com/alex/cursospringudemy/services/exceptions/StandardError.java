package com.alex.cursospringudemy.services.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer status;
	private String message;
	private Long timeStamp;
	public StandardError(Integer status, String msg, Long timeStamp) {
		super();
		this.status = status;
		this.message = msg;
		this.timeStamp = timeStamp;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String msg) {
		this.message = msg;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

}
