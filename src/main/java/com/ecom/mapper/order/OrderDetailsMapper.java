package com.ecom.mapper.order;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.ecom.dto.order.CreateOrderDetails;
import com.ecom.dto.order.response.OrderDetailsResponseDto;
import com.ecom.modal.order.Order;
import com.ecom.modal.order.OrderDetails;
import com.ecom.modal.product.Product;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDetailsMapper {

	@Mapping(target = "product",source = "productId",qualifiedByName="productIdToProduct")
	@Mapping(target = "order",source = "orderId",qualifiedByName="orderIdToOrder")
	OrderDetails dtoToEntity(OrderDetailsResponseDto dto);
	
	@Mapping(target = "productId",source = "product",qualifiedByName="productToProductId")
	@Mapping(target = "orderId",source = "order",qualifiedByName="orderToOrderId")
	OrderDetailsResponseDto entityToDto(OrderDetails entity);
	
	@Mapping(target = "product",source = "productId",qualifiedByName="productIdToProduct")
	OrderDetails createDtoToEntity(CreateOrderDetails createDetails);
	
	List<OrderDetails> dtoListToEntityList(List<OrderDetailsResponseDto> dto);
	
	List<OrderDetails> createDtoListToEntityList(List<OrderDetailsResponseDto> dto);
	
	List<OrderDetailsResponseDto> entityListToDtoList(List<OrderDetails> entity);
	
	@Named("productIdToProduct")
	public static Product productIdToProduct(Long id) {
		return new Product(id);
	}
	
	@Named("orderIdToOrder")
	public static Order orderIdToOrder(Long id) {
		return new Order(id);
	}
	
	@Named("productToProductId")
	public static Long productToProductId(Product product) {
		return product.getId();
	}
	
	@Named("orderToOrderId")
	public static Long orderToOrderId(Order order) {
		return order.getId();
	}
}
