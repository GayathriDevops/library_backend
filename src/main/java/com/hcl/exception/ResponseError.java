package com.hcl.exception;

public class ResponseError {
	
	String message;
	int statusCode;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public ResponseError(String message, int statusCode) {
		super();
		this.message = message;
		this.statusCode = statusCode;
	}
	
	

}
