package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>UserNotFoundException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public UserNotFoundException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructs a {@code UserNotFoundException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public UserNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
