package com.ecom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import com.ecom.dto.CreateUserDto;
import com.ecom.dto.UpdateUserDto;
import com.ecom.modal.User;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    
	User createUserDtoToUserModal(CreateUserDto createDto);
	
	void updateEntity(@MappingTarget User entity,UpdateUserDto dto);
	
	
}
