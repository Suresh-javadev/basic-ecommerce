package com.ecom.dto.product;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
	
	@ApiModelProperty( dataType = "String", example = "[cat1,cat2]")
	@NotEmpty(message = "category required")
	private List<ProductCategory> categories;
	
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
	public List<ProductCategory> getCategories() {
		return categories;
	}
	public void setCategories(List<ProductCategory> categories) {
		this.categories = categories;
	}
}
