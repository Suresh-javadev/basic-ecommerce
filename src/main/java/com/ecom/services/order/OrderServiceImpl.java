package com.ecom.services.order;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.ecom.dto.order.CreateOrder;
import com.ecom.dto.order.CreateOrderDetails;
import com.ecom.dto.order.CreatePayment;
import com.ecom.dto.order.OrderStatusDto;
import com.ecom.dto.order.response.OrderPaymentResponseDto;
import com.ecom.dto.order.response.OrderResponseDto;
import com.ecom.exception.ResourceNotFoundException;
import com.ecom.mapper.order.OrderDetailsMapper;
import com.ecom.mapper.order.OrderMapper;
import com.ecom.mapper.order.OrderPaymentMapper;
import com.ecom.modal.order.Order;
import com.ecom.modal.order.OrderDetails;
import com.ecom.modal.order.OrderPayment;
import com.ecom.modal.product.Product;
import com.ecom.repo.order.OrderPaymentRepository;
import com.ecom.repo.order.OrderRepository;
import com.ecom.services.product.ProductService;
import com.ecom.status.OrderStatus;
import com.ecom.status.PaymentOption;
import com.ecom.status.PaymentStatus;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepo;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailsMapper orderDetailsMapper;
	@Autowired
	private OrderPaymentMapper orderPaymentMapper;
	@Autowired
	private ProductService productService;
	@Autowired
	private OrderPaymentRepository orderPaymentRepo;
	
	private Order findOrder(Long orderId) throws ResourceNotFoundException{
		Assert.notNull(orderId, "The Order Id must not be null");
		
		Optional<Order> opsOrder = orderRepo.findById(orderId);
		
		if(opsOrder.isEmpty())
			throw new ResourceNotFoundException("Order not found by orderId: "+orderId);
		
		return opsOrder.get();
	}
	
	@Override
	public OrderResponseDto createOrder(CreateOrder order) {
		Assert.notNull(order, "The Order must not be null");
		
		Order orderToCreate = orderMapper.createDtoToEntity(order);
		orderToCreate.setStatus(OrderStatus.CREATED);
		
		
		AtomicReference<BigDecimal> totalPrice=new AtomicReference<>(BigDecimal.valueOf(0.0));
		
		order.getOrderDetails().forEach(details->{
			Product product=productService.findById(details.getProductId());
			//product price * countOf product --sum for total product
			totalPrice.getAndAccumulate(product.getPrice().multiply(BigDecimal.valueOf(details.getCount())), BigDecimal::add);
			
			OrderDetails orderDetails = orderDetailsMapper.createDtoToEntity(details);
			orderDetails.setOrder(orderToCreate);
			orderToCreate.getOrderDetails().add(orderDetails);
		});
		
		boolean validAmount = totalPrice.get().doubleValue() == order.getAmount().doubleValue();
		
		Assert.isTrue(validAmount, "Amount is not valid");
		
		Order created = orderRepo.save(orderToCreate);
		return orderMapper.enityToDto(created);
	}

	@Override
	public OrderResponseDto findOrderById(Long orderId) throws ResourceNotFoundException {

		return orderMapper.enityToDto(findOrder(orderId));
	}

	@Override
	@Transactional
	public OrderResponseDto updateOrder(Long orderId, CreateOrder orderUpdate) throws ResourceNotFoundException {
		Assert.notNull(orderUpdate, "Order for update can't be null");
		
		Order found = findOrder(orderId);
		
		Assert.isTrue(found.getStatus().equals(OrderStatus.CREATED), "Order can't be modified");
		
		Set<Long> validProductIds = orderUpdate.getOrderDetails()
				.stream()
				.map(CreateOrderDetails::getProductId)
				.collect(Collectors.toSet());
		//remove old product which not in new order for update
		found.getOrderDetails().removeIf(old-> !validProductIds.contains( old.getProduct().getId()));
		
		
		AtomicReference<BigDecimal> totalPrice=new AtomicReference<>(BigDecimal.valueOf(0.0));
		
		//update existing orderDetails
		found.getOrderDetails().forEach(orderDetailsExisting ->{
			Optional<CreateOrderDetails> orderDetailsForUpdate = orderUpdate.getOrderDetails()
					.stream()
					.filter(e-> e.getProductId().equals(orderDetailsExisting.getProduct().getId()))
					.findFirst();
			
			if(orderDetailsForUpdate.isPresent()) {
				//set new count of product(quantity)
				orderDetailsExisting.setCount(orderDetailsForUpdate.get().getCount());
				
				//remove from update list
				 orderUpdate.getOrderDetails().removeIf(e->e.getProductId().equals(orderDetailsExisting.getProduct().getId()));
				 
				//calculate new amount
				 totalPrice.getAndAccumulate(orderDetailsExisting.getProduct().getPrice().multiply(BigDecimal.valueOf(orderDetailsExisting.getCount())), BigDecimal::add);
			}		
		});
		
		//new product added in order calculate orderdetails.
		orderUpdate.getOrderDetails().forEach(details->{
			Product product=productService.findById(details.getProductId());
			//product price * countOf product --sum for total product
			totalPrice.getAndAccumulate(product.getPrice().multiply(BigDecimal.valueOf(details.getCount())), BigDecimal::add);
			
			OrderDetails orderDetails = orderDetailsMapper.createDtoToEntity(details);
			orderDetails.setOrder(found);
			found.getOrderDetails().add(orderDetails);
		});
		
		boolean validAmount = totalPrice.get().doubleValue() == orderUpdate.getAmount().doubleValue();
		
		Assert.isTrue(validAmount, "Amount is not valid");
		
		found.setAmount( orderUpdate.getAmount());
		
		return orderMapper.enityToDto(orderRepo.save(found));
	}

	@Override
	public void deleteOrder(Long orderId) throws ResourceNotFoundException {
		Order found = findOrder(orderId);	
		orderRepo.delete(found);
	}

	@Override
	public OrderStatusDto orderStatus(@NotNull Long id) throws ResourceNotFoundException {
		Optional<OrderStatus> opsStatus = orderRepo.findOrderStatusById(id);
		
		if(opsStatus.isEmpty())
			throw new ResourceNotFoundException("Order not found");
		
		return  new  OrderStatusDto(opsStatus.get());
	}

	@Override
	@Transactional
	public void updateOrderStatus(Long id, OrderStatusDto orderStatus) {
		Order found = findOrder(id);
		
		if(found.getStatus().equals(OrderStatus.CANCELLED) || found.getStatus().equals(OrderStatus.REJECTED)) {
			throw new IllegalStateException("Order not modificable");
		}
		
		if(orderStatus.getOrderStatus().equals(OrderStatus.PROCESSED)) {
			boolean payment = found.getOrderPayment() != null && (found.getOrderPayment().getOption().equals(PaymentOption.CASH) || found.getOrderPayment().getStatus().equals(PaymentStatus.PAID));
			Assert.isTrue(payment, "Payment Required CASH or Online Options");
		}else if(orderStatus.getOrderStatus().equals(OrderStatus.DELIVERED) ) {
			Assert.isTrue((found.getOrderPayment() !=null && found.getOrderPayment().getStatus().equals(PaymentStatus.PAID) ), "Payment Required CASH or Online Options");
		}
		
		orderRepo.updateOrderStatusById(id, orderStatus.getOrderStatus());

	}

	@Override
	public List<OrderResponseDto> findAll() {		
		return orderMapper.listEntityToDtoList(orderRepo.findAll());
	}

	@Override
	public OrderPaymentResponseDto createPayment( Long orderId, CreatePayment payment)
			throws ResourceNotFoundException {
		Order found = findOrder(orderId);
		
		Assert.isTrue(found.getOrderPayment()==null,"Payment Created Already");
		
		OrderPayment paymentEntity =new OrderPayment();	
		paymentEntity.setOrder(found);
		orderPaymentMapper.update(paymentEntity, payment);
		
		
		return orderPaymentMapper.entityToDto(orderPaymentRepo.save(paymentEntity));
	}

	@Override
	public OrderPaymentResponseDto updateOrderPaymentStatus(Long id, PaymentStatus status) {
		Order found = findOrder(id);	
		
		Assert.isTrue(found.getOrderPayment()!=null,"Payment not Created");
		
		OrderPayment paymentEntity = found.getOrderPayment();
		paymentEntity.setStatus(status);
		
		return orderPaymentMapper.entityToDto(orderPaymentRepo.save(paymentEntity));
	}
}
