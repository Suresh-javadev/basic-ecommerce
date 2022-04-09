package com.ecom.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecom.dto.product.CreateProduct;
import com.ecom.modal.product.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "lastModifiedDate", ignore = true)
	@Mapping(target = "categories", ignore = true)
	@Mapping(target = "id", ignore = true)
	Product createDtoToEntity(CreateProduct dto);
}
