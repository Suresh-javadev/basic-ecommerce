package com.ecom.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class UpdateUserDto {

	@ApiModelProperty(required = true,dataType = "String", example = "Suresh Kumar")
	@NotBlank(message = "name is mandatory")
	private String name;
	
	@ApiModelProperty(required = true,dataType = "String", example = "sk@gmail.com")
	@NotBlank(message = "Emaill is mandatory")
	@Email(regexp = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$")
	private String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
