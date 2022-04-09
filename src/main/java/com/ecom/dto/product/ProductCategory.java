package com.ecom.dto.product;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;

public class ProductCategory {
	@ApiModelProperty(required = true,dataType = "Long", example = "1")
	@NotBlank(message = "id is mandatory")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
