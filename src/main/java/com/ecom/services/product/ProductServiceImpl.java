package com.ecom.services.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.exception.ResourceAlreadyExistException;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.mapper.product.CategoryMapper;
import com.ecom.mapper.product.ProductMapper;
import com.ecom.modal.product.Category;
import com.ecom.modal.product.Product;
import com.ecom.repo.product.CategoryRepository;
import com.ecom.repo.product.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CategoryRepository categoryRepo;
	@Autowired
	private ProductMapper productMapper;
	@Autowired
	private CategoryMapper categoryMapper;
	
	@Override
	public Product create(CreateProduct product)throws ResourceAlreadyExistException {
		Assert.notNull(product, "The product must not be null");
		Assert.hasLength(product.getCode(), "The product code must not be null");
		
		Optional<Product> productOps = productRepo.findByCode(product.getCode());
		
		if(productOps.isPresent())
			throw new ResourceAlreadyExistException("Product Already Exist: "+product.getCode());
		
		return productRepo.save(productMapper.createDtoToEntity(product));
	}

	@Override
	public Category createCategory(CreateCategory category)throws ResourceAlreadyExistException {
		Assert.notNull(category, "The category must not be null");
		Assert.hasLength(category.getName(), "The category name must not be null");
		
		Optional<Category> categoryOps = categoryRepo.findByName(category.getName());
		
		if(categoryOps.isPresent())
			throw new ResourceAlreadyExistException("Category Already Exist: "+category.getName());
		
		return categoryRepo.save(categoryMapper.createDtoToEntity(category));
	}

	@Override
	public List<Product> list() {	
		return productRepo.findAll();
	}

	@Override
	public List<Category> categories() {
		return categoryRepo.findAll();
	}

	@Override
	public Product findById(Long productId) throws ResourceNotFoundException {
		Assert.notNull(productId, "The product id must not be null");
		
		
		Optional<Product> productOps = productRepo.findById(productId);
		
		if(productOps.isEmpty())
				throw new ResourceNotFoundException("Product not found");
		
		return productOps.get();
	}
	
	@Override
	public Category findCategoryById(Long categoryId) throws ResourceNotFoundException {
		Assert.notNull(categoryId, "The category id must not be null");
		
		
		Optional<Category> categoryOps = categoryRepo.findById(categoryId);
		
		if(categoryOps.isEmpty())
				throw new ResourceNotFoundException("Category not found");
		
		return categoryOps.get();
	}
	
	@Override
	public List<Category> findProductCategories(Long productId) {
		
		Product product =findById(productId);
		
		if(product.getCategories().isEmpty())
			throw new ResourceNotFoundException("Categories not found for productId: "+productId);
		
		return new ArrayList<>(product.getCategories());
	}

	@Override
	public void mapCategoryToProduct(Long productId, Long categoryId) {
		Product product = findById(productId);
		Category category = findCategoryById(categoryId);
		
		//add category to product
		product.getCategories().add(category);
		
		productRepo.save(product);
	}

	@Override
	public void unmapCategoryToProduct(Long productId, Long categoryId) {
		Product product = findById(productId);
		
		//add category to product
		product.getCategories().removeIf(cat->cat.getId().equals(categoryId));
		
		productRepo.save(product);
		
	}

	@Override
	public void delete(Long productId) {		
		productRepo.delete(findById(productId));
	}

	@Override
	public void deleteCategory(Long categoryId) {
		categoryRepo.delete(findCategoryById(categoryId));
	}

	@Override
	public Product update(@NotNull Long productId, CreateProduct productDto) {
		Product product = findById(productId);
		productMapper.updateEntity(product, productDto);
		
		return productRepo.save(product);
	}

}
