package com.ecom.dto.category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modal for create category")
public class CreateCategory {
	
	@ApiModelProperty(required = true,dataType = "String", example = "Electronics")
	@NotBlank(message = "{category.name.notblank}")
	@Size(min=2,max=100,message = "{category.name.size}")
	private String name;
	
	@ApiModelProperty( dataType = "String", example = "Category Electronics Products")
	private String description;
	
	public CreateCategory() {
		
	}
	
	public CreateCategory(String name,String description) {
		this.name =name;
		this.description =description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
}
