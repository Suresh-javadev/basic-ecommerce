package com.ecom.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.ecom.dto.product.CreateProduct;
import com.ecom.modal.product.Product;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {

	Product createDtoToEntity(CreateProduct dto);

	void updateEntity(@MappingTarget Product entity, CreateProduct updateEntity);
}
