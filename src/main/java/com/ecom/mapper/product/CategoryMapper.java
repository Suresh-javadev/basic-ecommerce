package com.ecom.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecom.dto.category.CreateCategory;
import com.ecom.modal.product.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "lastModifiedDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	Category createDtoToEntity(CreateCategory dto);
}
