package com.hcl.exception;

public class BookNotPresentException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotPresentException(String message) {
		super(message);
	}
}
