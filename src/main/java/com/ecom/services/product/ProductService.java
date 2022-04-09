package com.ecom.services.product;

import java.util.List;

import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.exception.ResourceAlreadyExistException;
import com.ecom.modal.product.Category;
import com.ecom.modal.product.Product;

public interface ProductService {

	public Product create(CreateProduct product) throws ResourceAlreadyExistException;
	public Category createCategory(CreateCategory category)throws ResourceAlreadyExistException;
	public List<Product> list();
	public List<Category> categories();
}
