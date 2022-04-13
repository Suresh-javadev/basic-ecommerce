package com.ecom.repo.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.modal.order.OrderPayment;

@Repository
public interface OrderPaymentRepository extends JpaRepository<OrderPayment, Long>{

}
