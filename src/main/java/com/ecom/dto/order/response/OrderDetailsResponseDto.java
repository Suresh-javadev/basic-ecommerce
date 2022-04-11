package com.ecom.dto.order.response;

public class OrderDetailsResponseDto {
	private Long id;
	private Long productId;
	private Long orderId;
	private Short count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Short getCount() {
		return count;
	}

	public void setCount(Short count) {
		this.count = count;
	}

}
