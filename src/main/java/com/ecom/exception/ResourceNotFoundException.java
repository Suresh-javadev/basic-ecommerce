package com.ecom.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructs a <code>ResourceNotFoundException</code> with the specified message.
	 * @param msg the detail message.
	 */
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructs a {@code ResourceNotFoundException} with the specified message and root
	 * cause.
	 * @param msg the detail message.
	 * @param cause root cause
	 */
	public ResourceNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}