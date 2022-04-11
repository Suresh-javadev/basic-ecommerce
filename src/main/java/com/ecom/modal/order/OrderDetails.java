package com.ecom.modal.order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ecom.modal.ModalTimeStamp;
import com.ecom.modal.product.Product;

@Entity
@Table(name = "tbl_order_details")
public class OrderDetails extends ModalTimeStamp{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "product_id", referencedColumnName = "id",nullable = false)
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "order_id", referencedColumnName = "id",nullable = false)
	private Order order;
	
	@Column(name="count",nullable = false)
	private Short count;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Short getCount() {
		return count;
	}

	public void setCount(Short count) {
		this.count = count;
	}
	
}
