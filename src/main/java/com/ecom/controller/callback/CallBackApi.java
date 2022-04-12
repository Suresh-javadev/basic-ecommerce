package com.ecom.controller.callback;

import org.springframework.http.ResponseEntity;

import com.ecom.status.PaymentStatus;

import io.swagger.annotations.Api;

@Api(value = "CallBack")
public interface CallBackApi {

	public ResponseEntity<String> paymentCallback(Long orderId,PaymentStatus status );
}
