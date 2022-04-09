package com.ecom.dto.product;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

public class CreateProduct {

	@ApiModelProperty(required = true,dataType = "String", example = "TV")
	@NotBlank(message = "name is mandatory")
	@Size(min=2,max=100)
	private String name;
	
	@ApiModelProperty(required = true,dataType = "String", example = "TV")
	@NotBlank(message = "code is mandatory")
	@Size(min=2,max=30)
	private String code;
	
	@ApiModelProperty( dataType = "String", example = "Television")
	private String description;
	
	public CreateProduct() {
		
	}
	
	public CreateProduct(String name,String code,String description) {
		this.name =name;
		this.code =code;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
