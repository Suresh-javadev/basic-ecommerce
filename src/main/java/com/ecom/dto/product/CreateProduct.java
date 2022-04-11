package com.ecom.dto.product;


import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modal for create product")
public class CreateProduct {

	@ApiModelProperty(required = true,dataType = "String", example = "TV")
	@NotBlank(message = "{product.name.notblank}")
	@Size(min=2,max=100,message = "{product.name.size}")
	private String name;
	
	@ApiModelProperty(required = true,dataType = "String", example = "P1")
	@NotBlank(message = "{product.code.notblank}")
	@Size(min=2,max=30,message = "{product.code.size}")
	private String code;
	
	@ApiModelProperty(required = true,dataType = "BigDecimal", example = "1000.00")
	@Digits(integer = 8,fraction = 2, message = "{product.price.digits}")
	private BigDecimal price;
	
	@ApiModelProperty( dataType = "String", example = "Television")
	private String description;
	
	public CreateProduct() {
		
	}
	
	public CreateProduct(String name,String code,BigDecimal price,String description) {
		this.name =name;
		this.code =code;
		this.price =price;
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
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
