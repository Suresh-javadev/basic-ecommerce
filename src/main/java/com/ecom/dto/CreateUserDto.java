package com.ecom.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.ecom.types.Roles;
import com.ecom.validators.ValidRoles;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modal for create user")
public class CreateUserDto {
	
	public CreateUserDto() {}
	
	public CreateUserDto(String name,
			String email,
			String username,
			String password,
			Roles role) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@ApiModelProperty(required = true,dataType = "String", example = "Suresh Kumar")
	@NotBlank(message = "{user.name.notblank}")
	@Size(min=4,max=100,message = "{user.name.size}")
	private String name;
	
	@ApiModelProperty(required = true,dataType = "String", example = "sk@gmail.com")
	@NotBlank(message = "{user.email.notblank}")
	@Email(regexp = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$",message = "{user.email.email}")
	private String email;
	
	@ApiModelProperty(required = true,dataType = "String", example = "sk")
	@NotBlank(message = "{user.username.notblank}")
	@Size(min=4,max=50,message = "{user.username.size}")
	private String username;
	
	@ApiModelProperty(required = true,dataType = "String", example = "abc@1234")
	@NotBlank(message = "{user.password.notblank}")
	@Size(min=8,max=50,message = "{user.password.size}")
	private String password;
	
	@ApiModelProperty(required = true,dataType = "String",example = "ADMIN")
	@NotNull
	@ValidRoles(anyOf = {Roles.ADMIN,Roles.USER},message = "{user.role.validroles}")
	private Roles role;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CreateUserDto other = (CreateUserDto) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
}
