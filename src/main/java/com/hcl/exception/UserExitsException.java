package com.hcl.exception;

public class UserExitsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserExitsException(String message) {
		super(message);
	}
}
