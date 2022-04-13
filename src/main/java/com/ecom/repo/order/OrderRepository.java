package com.ecom.repo.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecom.modal.order.Order;
import com.ecom.status.OrderStatus;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	@Modifying
	@Query("update Order odr set odr.status = :status where odr.id = :id")
	int updateOrderStatusById(@Param("id") Long id,@Param("status") OrderStatus status);
	
	@Query("select odr.status  from Order odr where odr.id = :id")
	Optional<OrderStatus> findOrderStatusById(@Param("id") Long id);
}
