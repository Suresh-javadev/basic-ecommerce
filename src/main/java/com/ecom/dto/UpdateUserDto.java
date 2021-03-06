package com.ecom.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import static com.ecom.constants.Constants.*;

@ApiModel(description = "Modal for update user")
public class UpdateUserDto {

	@ApiModelProperty(required = true,dataType = "String", example = "Suresh Kumar")
	@NotBlank(message = "{user.name.notblank}")
	@Size(min=4,max=100,message = "{user.name.size}")
	private String name;
	
	@ApiModelProperty(required = true,dataType = "String", example = "sk@gmail.com")
	@NotBlank(message = "{user.email.notblank}")
	@Email(regexp = EMIAL_REGEX,message = "{user.email.email}")
	private String email;

	public UpdateUserDto() {}
	
	public UpdateUserDto(String name,
			String email) {
		this.name = name;
		this.email = email;
	}

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		UpdateUserDto other = (UpdateUserDto) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
