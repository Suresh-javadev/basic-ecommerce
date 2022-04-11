package com.ecom.dto.order;

import com.ecom.status.OrderStatus;
import com.ecom.validators.order.ValidOrderStatus;

import io.swagger.annotations.ApiModelProperty;

public class OrderStatusDto {

	@ApiModelProperty(required = true,dataType = "Enum",example="PROCESSED")
	@ValidOrderStatus(anyOf = {OrderStatus.PROCESSED,OrderStatus.PROCESSED, OrderStatus.CANCELLED, OrderStatus.DELIVERED, OrderStatus.REJECTED} )
	private OrderStatus orderStatus;

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
