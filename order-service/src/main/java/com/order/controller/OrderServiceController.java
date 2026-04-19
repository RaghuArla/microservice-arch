package com.order.controller;

import java.util.List;

import com.order.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

@RestController
@RequestMapping("/order")
public class OrderServiceController {
	
	private final DiscoveryClient discoveryClient;
	
	private final RestClient restClient;
	
	@Value("${server.port}")
	private String port;

	public OrderServiceController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
		this.discoveryClient = discoveryClient;
		restClient = restClientBuilder.build();
	}
		
	@GetMapping("/hello")
	public String getOne() {
		return "hello order  !"+port;
	}
	@GetMapping("/getProducts")
	public List<Product> getProducts() {
		List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
		if (instances.isEmpty()) {
		    throw new RuntimeException("No instances found for servicea");
		}
		ServiceInstance serviceInstance = instances.get(0);


		List<Product> serviceAResponse = restClient.get()
				.uri(serviceInstance.getUri() + "/product/")
				.retrieve()
				.body(new ParameterizedTypeReference<List<Product>>() {});
		return serviceAResponse;
	}

}
