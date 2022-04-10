package com.ecom.controller.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.modal.product.Category;
import com.ecom.modal.product.Product;
import com.ecom.services.product.ProductService;

@RestController
public class ProductController implements ProductApi {
	
	@Autowired
	private ProductService productService;
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/product")
	public ResponseEntity<Product> create(@RequestBody @Valid CreateProduct product) {		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/product/{productId}")
	public ResponseEntity<Product> update(@PathVariable("productId") Long productId,@RequestBody @Valid CreateProduct product) {		
		return ResponseEntity.status(HttpStatus.OK).body(productService.update(productId,product));
	}	
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/product/category")
	public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategory category) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createCategory(category));
	}
	
	@Override
	@GetMapping("/products")
	public ResponseEntity<List<Product>> products() {
		return ResponseEntity.ok(productService.list());
	}
	
	@Override
	@GetMapping("/product/categories")
	public ResponseEntity<List<Category>> categories() {
		return ResponseEntity.ok(productService.categories());
	}
	
	@Override
	@GetMapping("/product/{productId}")
	public ResponseEntity<Product> product(@PathVariable("productId") Long productId) {
		return ResponseEntity.ok(productService.findById(productId));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/product/{productId}")
	public ResponseEntity<String> delete(Long productId) {		
		productService.delete(productId);
		return ResponseEntity.ok("Deleted Successfully");
	}
	
	@Override
	@GetMapping("/product/{productId}/categories")
	public ResponseEntity<List<Category>> productCategory(@PathVariable("productId") Long productId) {
		List<Category> list=productService.findProductCategories(productId);
		return ResponseEntity.ok(list);
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/product/{productId}/category/{categoryId}")
	public ResponseEntity<String> mapCategory(@PathVariable("productId") Long productId,@PathVariable("categoryId") Long categoryId) {
		productService.mapCategoryToProduct(productId,categoryId);
		return ResponseEntity.ok("Mapped Successfully");
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/product/{productId}/category/{categoryId}")
	public ResponseEntity<String> unmapCategory(@PathVariable("productId") Long productId,@PathVariable("categoryId") Long categoryId) {
		productService.unmapCategoryToProduct(productId,categoryId);
		return ResponseEntity.ok("Unmapped Successfully");
	}
	
	@Override
	@GetMapping("/product/category/{categoryId}")
	public ResponseEntity<Category> category(@PathVariable("categoryId") Long id) {
		return ResponseEntity.ok(productService.findCategoryById(id));
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/product/category/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable("categoryId") Long id) {
		productService.deleteCategory(id);
		return ResponseEntity.ok("Deleted Successfully");
	}

}
