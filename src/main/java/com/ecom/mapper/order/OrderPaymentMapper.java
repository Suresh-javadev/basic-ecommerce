package com.ecom.mapper.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import com.ecom.dto.order.response.OrderPaymentResponseDto;
import com.ecom.modal.order.Order;
import com.ecom.modal.order.OrderPayment;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderPaymentMapper {
	
	@Mapping(target = "order",source = "orderId",qualifiedByName="orderIdToOrder")
	OrderPayment dtoToEntity(OrderPaymentResponseDto dto );
	
	@Mapping(target = "orderId",source = "order",qualifiedByName="orderToOrderId")
	OrderPaymentResponseDto entityToDto(OrderPayment entity );
	
	@Named("orderIdToOrder")
	public static Order orderIdToOrder(Long id) {
		return new Order(id);
	}
	
	@Named("orderToOrderId")
	public static Long orderToOrderId(Order order) {
		return order.getId();
	}
}
