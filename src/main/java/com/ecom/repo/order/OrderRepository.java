package com.ecom.repo.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.modal.order.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
