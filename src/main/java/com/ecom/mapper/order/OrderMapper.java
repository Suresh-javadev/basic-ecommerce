package com.ecom.mapper.order;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.ecom.dto.order.CreateOrder;
import com.ecom.dto.order.response.OrderResponseDto;
import com.ecom.modal.User;
import com.ecom.modal.order.Order;

@Mapper(componentModel = "spring",uses = {OrderDetailsMapper.class,OrderPaymentMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper {

	@Mapping(target = "user",source = "userId",qualifiedByName="userIdToUser")
	Order dtoToEntity(OrderResponseDto orderDto);
	@Mapping(target = "userId",source = "user",qualifiedByName="userToUserId")
	OrderResponseDto enityToDto(Order order);
	
	@Mapping(target = "user",source = "userId",qualifiedByName="userIdToUser")
	@Mapping(target="orderDetails",ignore = true)
	Order createDtoToEntity(CreateOrder create);
	
	@Mapping(target = "user",ignore = true)
	void updateEntity(@MappingTarget Order order,CreateOrder update);
		
	List<Order> listDtoToEntityList(List<OrderResponseDto> list);
	List<OrderResponseDto> listEntityToDtoList(List<Order> list);
	
	@Named("userIdToUser")
	public static User userIdToUser(Long userId) {
		return new User(userId);
	}
	
	@Named("userToUserId")
	public static Long userToUserId(User user) {
		return user.getId();
	}
}
