package com.ecom.mapper.product;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.ecom.dto.category.CreateCategory;
import com.ecom.modal.product.Category;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

	Category createDtoToEntity(CreateCategory dto);
}
