package com.payment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@CircuitBreaker(name = "sampleService", fallbackMethod = "fallback")
	@GetMapping("/hello")
	public String getOne() {
		System.out.println("hello product");
		throw new RuntimeException("failed");
	}

	public String fallback(RuntimeException ex) {
		System.out.println("Fallback triggered due to: " + ex.getMessage());
		return "hello produtc-service getFallback!";
	}

}
