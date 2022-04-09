package com.ecom.modal.product;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.ecom.modal.ModalTimeStamp;

@Entity
@Table(name = "tbl_product")
public class Product extends ModalTimeStamp{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name", nullable = false, length = 150)
	private String name;
	
	@Column(name="code", nullable = false, unique = true,length = 30)
	private String code;
	
	@Column(name="description", length = 1000)
	private String description;
	
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tbl_product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    private Collection<Category> category;
}
