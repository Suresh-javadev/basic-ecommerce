package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT)
public class UserAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>UserAlreadyExistException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public UserAlreadyExistException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructs a {@code UserAlreadyExistException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public UserAlreadyExistException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
