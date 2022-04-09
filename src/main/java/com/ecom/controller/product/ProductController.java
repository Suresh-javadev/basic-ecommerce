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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.category.CreateCategory;
import com.ecom.dto.product.CreateProduct;
import com.ecom.modal.product.Category;
import com.ecom.modal.product.Product;
import com.ecom.services.product.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController implements ProductApi {
	@Autowired
	private ProductService productService;
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Product> create(@RequestBody @Valid CreateProduct product) {		
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.create(product));
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/category/create")
	public ResponseEntity<Category> createCategory(@RequestBody @Valid CreateCategory category) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.createCategory(category));
	}
	
	@Override
	@GetMapping("/list")
	public ResponseEntity<List<Product>> products() {
		return ResponseEntity.ok(productService.list());
	}
	
	@Override
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> categories() {
		return ResponseEntity.ok(productService.categories());
	}
	
	@Override
	@GetMapping("/{productId}")
	public ResponseEntity<Product> product(@PathVariable("productId") Long productId) {

		return null;
	}

	@Override
	@GetMapping("/{productId}/categories")
	public ResponseEntity<List<Category>> productCategory(@PathVariable("productId") Long productId) {

		return null;
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{productId}/category/{categoryId}")
	public ResponseEntity<String> unmapCategory(@PathVariable("productId") Long productId,@PathVariable("categoryId") Long categoryId) {

		return null;
	}
	
	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{productId}/category/{categoryId}")
	public ResponseEntity<String> mapCategory(@PathVariable("productId") Long productId,@PathVariable("categoryId") Long categoryId) {

		return null;
	}
	
	@Override
	@GetMapping("/category/{categoryId}")
	public ResponseEntity<Category> category(Long id) {

		return null;
	}
	
	@Override
	@DeleteMapping("/category/{categoryId}")
	public ResponseEntity<String> deleteCategory(Long id) {

		return null;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> delete(Long productId) {
		
		return null;
	}

}
