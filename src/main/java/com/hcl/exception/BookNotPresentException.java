package com.hcl.exception;

/**
 * 
 * @author Pradeepa AJ
 *
 */

public class BookNotPresentException  extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BookNotPresentException(String message) {
		super(message);
	}
}
