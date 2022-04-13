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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		CreateOrderDetails other = (CreateOrderDetails) obj;
		if (count == null) {
			if (other.count != null)
				return false;
		} else if (!count.equals(other.count))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	
	
}
