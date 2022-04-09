package com.ecom.controller.product;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.product.CreateProduct;
import com.ecom.modal.product.Product;

@RestController
@RequestMapping("/product")
public class ProductController implements ProductApi {

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Product> create(@RequestBody @Valid CreateProduct product) {
		
		return null;
	}

	@Override
	@GetMapping("/{productId}")
	public ResponseEntity<Product> product(Long productId) {

		return null;
	}

	@Override
	@GetMapping("/list")
	public ResponseEntity<List<Product>> products() {
		
		return null;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{productId}")
	public ResponseEntity<String> delete(Long productId) {
		
		return null;
	}

}
