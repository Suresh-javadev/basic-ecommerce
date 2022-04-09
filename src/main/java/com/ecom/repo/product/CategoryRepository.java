package com.ecom.repo.product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.modal.product.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	Optional<Category> findByName(String name);
}
