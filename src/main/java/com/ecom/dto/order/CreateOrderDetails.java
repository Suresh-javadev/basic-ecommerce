package com.ecom.dto.order;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Modal for orderDetails")
public class CreateOrderDetails {
	
	@ApiModelProperty(required = true,dataType = "Long", example = "1")
	@NotNull(message = "{orderdetails.productid.notnull}")
	@Range(min=1, message = "{orderdetails.productid.range}")
	private Long productId;
	
	@ApiModelProperty(required = true,dataType = "Short", example = "2")
	@NotNull(message = "{orderdetails.count.notnull}")
	@Range(min=1,max=10,message = "{orderdetails.count.range}")
	private Short count;

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Short getCount() {
		return count;
	}

	public void setCount(Short count) {
		this.count = count;
	}
	
}
