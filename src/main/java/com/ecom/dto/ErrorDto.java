package com.ecom.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;


public class ErrorDto {
	@ApiModelProperty(dataType = "Integer", example = "200")
	private HttpStatus httpStatus;

	@ApiModelProperty(dataType = "Date", example = "2022-04-09 10:24:37")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="Asia/Kolkata")
	private LocalDateTime timestamp;

	@ApiModelProperty(dataType = "String", example = "Validation Error")
	private String message;

	@ApiModelProperty(dataType = "String", example = "email Emaill is mandatory")
	private String details;

	public ErrorDto(HttpStatus httpStatus, String message, String details) {
		this.httpStatus = httpStatus;
		this.timestamp = LocalDateTime.now();
		this.message = message;
		this.details = details;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

}
