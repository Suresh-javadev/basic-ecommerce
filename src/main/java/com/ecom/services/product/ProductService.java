package com.ecom.services.product;

import java.util.List;

import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.exception.ResourceAlreadyExistException;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.modal.product.Category;
import com.ecom.modal.product.Product;

public interface ProductService {

	public Product create(CreateProduct product) throws ResourceAlreadyExistException;
	public Category createCategory(CreateCategory category)throws ResourceAlreadyExistException;
	public List<Product> list();
	public List<Category> categories();
	public Product findById(Long productId) throws ResourceNotFoundException;
	public Category findCategoryById(Long categoryId) throws ResourceNotFoundException;
	public List<Category> findProductCategories(Long productId);
	public void mapCategoryToProduct(Long productId, Long categoryId);
	public void unmapCategoryToProduct(Long productId, Long categoryId);
	public void delete(Long productId);
	public void deleteCategory(Long categoryId);
	public Product update(Long productId, CreateProduct product);
}
