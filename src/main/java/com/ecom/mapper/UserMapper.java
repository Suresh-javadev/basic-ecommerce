package com.ecom.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.ecom.dto.CreateUserDto;
import com.ecom.modal.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
	@Mapping(target = "accountNonExpired", ignore = true)
	@Mapping(target = "accountNonLocked", ignore = true)
	@Mapping(target = "credentialsNonExpire", ignore = true)
	@Mapping(target = "enabled", ignore = true)
	@Mapping(target = "createdDate", ignore = true)
	@Mapping(target = "lastModifiedDate", ignore = true)
	@Mapping(target = "id", ignore = true)
	User createUserDtoToUserModal(CreateUserDto createDto);
}
