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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
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
		CreateCategory other = (CreateCategory) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}	
	
	
}
