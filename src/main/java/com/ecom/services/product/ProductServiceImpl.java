package com.ecom.services.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.exception.ResourceAlreadyExistException;
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

}
