package com.ecom.dto.order;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Modal for orderDetails")
public class CreateOrderDetails {
	
	@NotNull(message = "{orderdetails.productid.notnull}")
	@Size(min=1,message = "{orderdetails.productid.size}")
	private Long productId;
	
	@NotNull(message = "{orderdetails.count.notnull}")
	@Size(min=1,max=10,message = "{orderdetails.count.size}")
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
