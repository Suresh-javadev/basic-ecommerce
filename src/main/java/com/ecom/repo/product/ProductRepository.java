package com.ecom.repo.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.modal.product.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Optional<Product> findByCode(String code);
}
