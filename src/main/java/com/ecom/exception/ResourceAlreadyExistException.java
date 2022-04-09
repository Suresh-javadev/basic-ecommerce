package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.CONFLICT)
public class ResourceAlreadyExistException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>ResourceAlreadyExistException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public ResourceAlreadyExistException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructs a {@code ResourceAlreadyExistException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public ResourceAlreadyExistException(String msg, Throwable cause) {
		super(msg, cause);
	}

}