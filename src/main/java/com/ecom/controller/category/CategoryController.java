package com.ecom.controller.category;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dto.category.CreateCategory;
import com.ecom.modal.product.Category;

@RestController
@RequestMapping("/category")
public class CategoryController implements CategoryApi{

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<Category> create(@RequestBody @Valid CreateCategory cat) {
		
		return null;
	}

	@Override
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> category(@PathVariable("categoryId") Long catId) {
		
		return null;
	}

	@Override
	@GetMapping("/list")
	public ResponseEntity<List<Category>> category() {
		
		return null;
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<String> delete(@PathVariable("categoryId") Long catId) {
		
		return null;
	}

}
